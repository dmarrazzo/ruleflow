package com.myspace.filter;

import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

public class PackageFilter implements AgendaFilter {

    private String pkgName;

    /**
     * @param pkgName
     */
    public PackageFilter(String pkgName) {
        this.pkgName = pkgName;
    }
    
	@Override
	public boolean accept(Match match) {
		return match.getRule().getPackageName().contentEquals(pkgName);
	}
}