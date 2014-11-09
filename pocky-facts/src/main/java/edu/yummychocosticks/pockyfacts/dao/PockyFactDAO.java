package edu.yummychocosticks.pockyfacts.dao;

import javax.jdo.PersistenceManager;

import edu.yummychocosticks.pockyfacts.PMF;
import edu.yummychocosticks.pockyfacts.entity.PockyFactEntity;

public enum PockyFactDAO {
	INSTANCE;
	
	public PockyFactEntity get(long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		PockyFactEntity result = null;
		try {
			result = pm.getObjectById(PockyFactEntity.class, id);
		} finally {
			pm.close();
		}
		return result;
	}

	public PockyFactEntity getRandom() {
		return this.get(0);
	}

	public PockyFactEntity add(String fact) {
		PockyFactEntity result = new PockyFactEntity();
		result.setFact(fact);
		synchronized (this) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				pm.makePersistent(result);
			} finally {
				pm.close();
			}
		}
		return result;
	}

	public void remove(Long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		PockyFactEntity result;
		try {
			result = pm.getObjectById(PockyFactEntity.class, id);
			pm.deletePersistent(result);
		} finally {
			pm.close();
		}
	}

}
