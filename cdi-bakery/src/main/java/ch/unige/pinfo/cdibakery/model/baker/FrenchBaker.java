package ch.unige.pinfo.cdibakery.model.baker;

import javax.enterprise.inject.Default;

@Default
public class FrenchBaker implements IBaker {

	@Override
	public String bakeDish() {
		return "An awesome macron from the French baker";
	}

}
