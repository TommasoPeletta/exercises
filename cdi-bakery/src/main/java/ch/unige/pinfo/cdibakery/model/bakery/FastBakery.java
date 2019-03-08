package ch.unige.pinfo.cdibakery.model.bakery;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import ch.unige.pinfo.cdibakery.model.baker.IBaker;

@Default
public class FastBakery implements IBakery {

//	private IBaker baker = new FrenchBaker();
	
	@Inject
	private IBaker baker;
	
	@Override
	public String bake() {
		return "You will get " + this.baker.bakeDish() 
		+ ". And it will arrive fast";
	}

}
