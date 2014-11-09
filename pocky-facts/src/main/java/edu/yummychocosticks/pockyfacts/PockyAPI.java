package edu.yummychocosticks.pockyfacts;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import edu.yummychocosticks.pockyfacts.dao.PockyFactDAO;
import edu.yummychocosticks.pockyfacts.entity.PockyFactEntity;

@Api(name = "pockyfacts",
     version = "v1",
     scopes = {Constants.EMAIL_SCOPE},
     clientIds = {Constants.WEB_CLIENT_ID},
     namespace = @ApiNamespace(ownerDomain = "pockyfacts.yummychocosticks.edu",
                                ownerName = "pockyfacts.yummychocosticks.edu",
                                packagePath=""))
public class PockyAPI {

    @ApiMethod(name = "getFact", path="fact")
    public PockyFactEntity getFact(@Named("id") int id) {
        return PockyFactDAO.INSTANCE.get(id);
    }

    @ApiMethod(name = "getRandomFact", path="fact/random")
    public PockyFactEntity getRandomFact() {
        return PockyFactDAO.INSTANCE.getRandom();
    }

    @ApiMethod(name = "createFact", path="fact", httpMethod = "post")
    public void createFact(@Named("fact") String fact) {
    	PockyFactDAO.INSTANCE.add(fact);
    }

    @ApiMethod(name = "deleteFact", path="fact", httpMethod = "delete")
    public void deleteFact(@Named("fact") String fact) {
        PockyFactDAO.INSTANCE.add(fact);
    }
}
