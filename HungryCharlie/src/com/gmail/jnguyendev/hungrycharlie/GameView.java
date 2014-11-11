package com.gmail.jnguyendev.hungrycharlie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.gmail.jnguyendev.hungrycharlie.R;
import com.gmail.jnguyendev.hungrycharlie.data.GameTileData;

//import com.gmail.jnguyendev.hungrycharlie.PlayerUnit;

//import com.gmail.jnguyendev.hungrycharlie.data.GameLevelTileData;
//import com.gmail.jnguyendev.hungrycharlie.data.GameTileData;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * The game view and main game thread.
 * 
 * GameView creates a new thread (GameThread) to handle all calculations
 * and drawing of game components.
 * 
 * GameThread contains the run() function, which serves as the game loop,
 * updating each cycle while the game is running.
 * 
 * To see how game level data is parsed and turned into a playable, tile level,
 * see the function GameView.parseGameLevelData.
 * 
 * @author Dan Ruscoe (ruscoe.org)
 * @version 1.0
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback
{

	public static final int STATE_RUNNING = 1;
	public static final int STATE_PAUSED = 2;

	private int mScreenXMax = 0;
	private int mScreenYMax = 0;
	private int mScreenXOffset = 0;
	private int mScreenYOffset = 0;

	private Context mGameContext;
	private Play mGameActivity;
	private SurfaceHolder mGameSurfaceHolder = null;

	private boolean updatingGameTiles = false;

	private Bitmap mBackgroundImage = null;

	private int mGameState;

	private boolean mGameRun = true;
	
	private Paint mUiTextPaint = null;
	private String mLastStatusMessage = "";

	/**
	 * GameTile instances for each game tile used by the current level.
	 */
	private List<GameTile> mGameTiles = Collections.synchronizedList(new ArrayList<GameTile>());

	class GameThread extends Thread
	{
		public GameThread(SurfaceHolder surfaceHolder, Context context,
				Handler handler)
		{
			mGameSurfaceHolder = surfaceHolder;
			mGameContext = context;

			Resources res = context.getResources();

			mBackgroundImage = BitmapFactory.decodeResource(res, R.drawable.canvas_bg_01);

			Display display = mGameActivity.getWindowManager().getDefaultDisplay();
			mScreenXMax = display.getWidth();
			mScreenYMax = display.getHeight();

			Log.d("HungryCharlie", "Screen X: " + mScreenXMax + ", Y: " + mScreenYMax);
		}

		/**
		 * Callback invoked when the surface dimensions change.
		 */
		public void setSurfaceSize(int width, int height)
		{
			// synchronized to make sure these all change atomically
			synchronized (mGameSurfaceHolder)
			{
				mBackgroundImage = Bitmap.createScaledBitmap(mBackgroundImage,
						width, height, true);
			}
		}

		/**
		 * Sets the run status of the game loop inside the game thread.
		 * @param boolean run - true when game should run, false otherwise.
		 */
		public void setRunning(boolean run)
		{
			mGameRun = run;
		}

		/**
		 * Sets the game state to running.
		 */
		public void doStart()
		{
			setState(STATE_RUNNING);
		}

		/**
		 * Sets the game state
		 * @param int mode - May be STATE_RUNNING or STATE_PAUSED
		 */
		public void setState(int state)
		{
			mGameState = state;
		}
		
		/**
		 * Contains the main game loop, which updates all elements of the game.
		 */
		@Override
		public void run()
		{
			while (mGameRun)
			{
				Canvas c = null;
				try
				{
					c = mGameSurfaceHolder.lockCanvas(null);
					synchronized (mGameSurfaceHolder)
					{
						doDraw(c);
					}
				} finally
				{
					if (c != null)
					{
						mGameSurfaceHolder.unlockCanvasAndPost(c);
					}
				}
			}

			return;
		}

		/**
		 * Pauses the game.
		 */
		public void pause()
		{
			synchronized (mGameSurfaceHolder)
			{
				if (mGameState == STATE_RUNNING)
				{
					setState(STATE_PAUSED);
				}
			}
		}

		/**
		 * Unpauses the game.
		 */
		public void unpause()
		{
			synchronized (mGameSurfaceHolder)
			{
				if (mGameState != STATE_RUNNING)
				{
					setState(STATE_RUNNING);
				}
			}
		}

		/**
		 * Draws all visual elements of the game.
		 * @param Canvas canvas
		 */
		private void doDraw(Canvas canvas)
		{
			if (canvas != null)
			{
				if (!updatingGameTiles)
				{
					drawGameTiles(canvas);
				}

				canvas.drawText(mLastStatusMessage, 30, 50, mUiTextPaint);
			}
		}

		/**
		 * Draws the game tiles used in the current level.
		 * @param Canvas canvas
		 */
		private void drawGameTiles(Canvas canvas)
		{
			synchronized(mGameTiles) {
				Iterator<GameTile> it = mGameTiles.iterator();
				GameTile g;
				while (it.hasNext()) {
					g = it.next();
					if (g != null)
					{
						g.setX(g.getX() - mScreenXOffset);
						g.setY(g.getY() - mScreenYOffset);
						if (g.isVisible())
						{
							canvas.drawBitmap(g.getBitmap(), g.getX(), g.getY(), null);
						}
					}
				}				
			}
		}
	}

	private GameThread thread;

	/**
	 * The game view.
	 * @param Context context
	 * @param Activity activity
	 * @param int stage - The stage to load.
	 * @param int level - The level to load.
	 * @param float screenDensity - The screen density.
	 */
	public GameView(Context context, Play activity)
	{
		super(context);

		mGameContext = context;
		mGameActivity = activity;
		
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);

		// create thread only; it's started in surfaceCreated()
		thread = new GameThread(holder, context, null);

		setFocusable(true);

		mUiTextPaint = new Paint();
		mUiTextPaint.setStyle(Paint.Style.FILL);
		mUiTextPaint.setColor(Color.YELLOW);
		mUiTextPaint.setAntiAlias(true);
		
		Typeface uiTypeface = Typeface.createFromAsset(activity.getAssets(), "fonts/Molot.otf");
		if (uiTypeface != null)
		{
			mUiTextPaint.setTypeface(uiTypeface);
		}
		mUiTextPaint.setTextSize(mGameContext.getApplicationContext().getResources().getDimensionPixelSize(R.dimen.ui_text_size));

		startLevel();
		thread.doStart();
	}

	/**
	 * Gets the game thread.
	 * @return GameThread
	 */
	public GameThread getThread()
	{
		return thread;
	}

	/**
	 * Callback invoked when the surface dimensions change.
	 */
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height)
	{
		thread.setSurfaceSize(width, height);
	}

	/*
	 * Callback invoked when the Surface has been created and is ready to be
	 * used.
	 */
	public void surfaceCreated(SurfaceHolder holder)
	{
		// start the thread here so that we don't busy-wait in run()
		// waiting for the surface to be created

		if (thread.getState() == Thread.State.TERMINATED)
		{
			thread = new GameThread(holder, getContext(), new Handler());
			thread.setRunning(true);
			thread.start();
			thread.doStart();
			startLevel();
		}
		else
		{
			thread.setRunning(true);
			thread.start();
		}
	}

	/*
	 * Callback invoked when the Surface has been destroyed.
	 */
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		boolean retry = true;
		thread.setRunning(false);
		while (retry)
		{
			try
			{
				thread.join();
				retry = false;
			} catch (InterruptedException e)
			{
				Log.e("Tile Game Example", e.getMessage());
			}
		}
	}

	private GameTile findSafeTile() {
		GameTile max = null;
		synchronized(mGameTiles) {
			Iterator<GameTile> it = mGameTiles.iterator();
			GameTile g;
			while (it.hasNext()) {
				g = it.next();
				if (!g.isDangerous()) {
					if (max == null || g.getY() > max.getY()) {
						max = g;
					}
				}
			}
		}
		return max;
	}
	
	public void generateRow() {
		updatingGameTiles = true;

		synchronized(mGameTiles) {
			Iterator<GameTile> it = mGameTiles.iterator();
			GameTile g;
			while (it.hasNext()) {
				g = it.next();
				g.setY(g.getY() + mScreenYMax/4);
				if (g.getY() >= mScreenYMax) {
					it.remove();
				}
			}

			Random r = new Random();
			int safe = r.nextInt(4);
			GameTile gameTile;
			for (int i = 0; i < 4; i++) {
				Point point = new Point(i * mScreenXMax/4, 0);
				int drawable = (i == safe) ? R.drawable.safe_tile : R.drawable.danger_tile;
				gameTile = new GameTile(mGameContext, drawable, point);
				gameTile.setBitmap(Bitmap.createScaledBitmap(gameTile.getBitmap(), mScreenXMax/4, mScreenYMax/4, false));
				gameTile.setType((i == safe) ? GameTile.TYPE_EMPTY : GameTile.TYPE_DANGEROUS);
				mGameTiles.add(gameTile);
			}
		}
		updatingGameTiles = false;
	}
	
	/**
	 * Detects and handles touch events from the user.
	 * @param MotionEvent event
	 * @return boolean
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int eventAction = event.getAction();
		switch (eventAction)
		{
		case MotionEvent.ACTION_DOWN:

			if (mGameState == STATE_RUNNING)
			{
				final int x = (int) event.getX();
				final int y = (int) event.getY();
				GameTile safe = findSafeTile();
				if (safe != null) {
					if (safe.getImpact(x, y)) {
						generateRow();
					} else {
						// TODO: show "You lost" message
					}	
				}
			}

			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
		}

		return true;
	}
	
	/**
	 * Loads and starts the current level.
	 */
	private void startLevel()
	{
		for (int i = 0; i < 4; i++) {
			generateRow();
		}

		thread.unpause();
	}

}
