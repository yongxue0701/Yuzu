package sg.edu.nus.learnandroid.common;

/**
 * Created by Yongxue on 21/10/17.
 */

public class ItemView {

    private String viewName;
    private String viewType;

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public ItemView(String viewName, String viewType) {
        this.viewName = viewName;
        this.viewType = viewType;
    }
}
