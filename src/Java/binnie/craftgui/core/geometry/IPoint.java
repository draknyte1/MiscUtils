package binnie.craftgui.core.geometry;

public class IPoint
{
  public IPoint(float x, float y)
  {
    this.x = 0.0F;
    this.y = 0.0F;
    this.x = x;
    this.y = y;
  }
  
  public IPoint(IPoint o)
  {
    this.x = 0.0F;
    this.y = 0.0F;
    this.x = o.x();
    this.y = o.y();
  }
  
  public static IPoint add(IPoint a, IPoint b)
  {
    return new IPoint(a.x() + b.x(), a.y() + b.y());
  }
  
  public static IPoint sub(IPoint a, IPoint b)
  {
    return new IPoint(a.x() - b.x(), a.y() - b.y());
  }
  
  public IPoint sub(IPoint a)
  {
    return sub(this, a);
  }
  
  public IPoint add(IPoint other)
  {
    return add(this, other);
  }
  
  public IPoint add(float dx, float dy)
  {
    return add(this, new IPoint(dx, dy));
  }
  
  public IPoint copy()
  {
    return new IPoint(this);
  }
  
  public float x()
  {
    return this.x;
  }
  
  public float y()
  {
    return this.y;
  }
  
  public void xy(float x, float y)
  {
    x(x);
    y(y);
  }
  
  public float x(float x)
  {
    return this.x = x;
  }
  
  public float y(float y)
  {
    return this.y = y;
  }
  
  public boolean equals(IPoint other)
  {
    return (x() == other.x()) && (y() == other.y());
  }
  
  public static final IPoint ZERO = new IPoint(0.0F, 0.0F);
  float x;
  float y;
}
