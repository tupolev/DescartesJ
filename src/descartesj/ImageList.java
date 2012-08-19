package descartesj;

import java.util.*;

public class ImageList {
    private int current = 0;
    private List<DImage> imageList;
    
    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    private List<DImage> getImageList() {
        return imageList;
    }

    private void setImageList(List<DImage> imageList) {
        this.imageList = imageList;
    }
        
    public List<DImage> getList() {
        return this.getImageList();
    }

    public ImageList() {
        this.setImageList(new ArrayList<DImage>());
    }

    public boolean add(DImage image) {
        this.getImageList().add(image);
        return (this.getImageList().lastIndexOf(image) >= 0);
    }

    public int count() {
        return this.getImageList().size();
    }

    public String selectCurrent() {
        this.getImageList().get(this.getCurrent()).setStatus("selected");
        return this.getImageList().get(this.getCurrent()).getStatus();
    }

    public String discardCurrent()
    {
       this.getImageList().get(this.getCurrent()).setStatus("discarded");
       return this.getImageList().get(this.getCurrent()).getStatus();
    }
}
