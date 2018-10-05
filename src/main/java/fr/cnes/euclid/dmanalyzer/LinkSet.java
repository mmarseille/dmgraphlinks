package fr.cnes.euclid.dmanalyzer;

import java.util.HashMap;
import java.util.Map;

public class LinkSet {

    String linkSource;

    Map<String, Integer> linkTargets;

    public LinkSet(String source) {
        this.linkSource = source;
        this.linkTargets = new HashMap<>();
    }

    public void addTarget(String target, Integer weight) {
        this.linkTargets.put(target, weight);
    }

    public String getLinkSource() {
        return linkSource;
    }

    public Map<String, Integer> getLinkTargets() {
        return linkTargets;
    }
}
