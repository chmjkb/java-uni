public interface SimpleDrawing {
    public void setCanvasGeometry(Geometry input);
    public void draw(Segment segment);
    public int[][] getPainting();
   public void clear();
}