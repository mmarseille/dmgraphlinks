package fr.cnes.euclid.dmanalyzer;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DmAnalysis implements Iterable<LinkSet> {

    private String fileName;

    private JSONObject jsonObject;

    private List<LinkSet> linkSetList;

    public DmAnalysis(String filename) {
        this.fileName = filename;
        this.jsonObject = new JSONObject(new JSONTokener(DmAnalysis.class.getClassLoader().getResourceAsStream(fileName)));
        this.linkSetList = new ArrayList<>();

        this.jsonObject.keys().forEachRemaining(key -> {
            LinkSet linkSet = new LinkSet(key);
            JSONObject inner = (JSONObject) this.jsonObject.get(key);
            inner.toMap().forEach((inner_key, value) -> {
               linkSet.addTarget(inner_key, (Integer) value);
            });
            this.linkSetList.add(linkSet);
        });
    }

    public String getFileName() {
        return fileName;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    @Override
    public Iterator<LinkSet> iterator() {
        return linkSetList.iterator();
    }

    public enum RelationType {
        DEPENDS_ON
    }

}
