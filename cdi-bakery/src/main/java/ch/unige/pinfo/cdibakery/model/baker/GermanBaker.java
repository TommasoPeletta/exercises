package ch.unige.pinfo.cdibakery.model.baker;

import javax.enterprise.inject.Alternative;

@Alternative
public class GermanBaker implements IBaker {

	@Override
	public String bakeDish() {
		return "A very precise BlackForrestCake" +
				"from the German baker";
	}

}
