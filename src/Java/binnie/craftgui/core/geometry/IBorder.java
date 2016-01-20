package binnie.craftgui.core.geometry;

public class IBorder
{
  public IBorder(float pad)
  {
    this(pad, pad, pad, pad);
  }
  
  public IBorder(float tb, float rl)
  {
    this(tb, rl, tb, rl);
  }
  
  public IBorder(float t, float rl, float b)
  {
    this(t, rl, b, rl);
  }
  
  public IBorder(float t, float r, float b, float l)
  {
    this.t = t;
    this.b = b;
    this.l = l;
    this.r = r;
  }
  
  public IBorder(Position edge, float n)
  {
    this(edge == Position.Top ? n : 0.0F, edge == Position.Right ? n : 0.0F, edge == Position.Bottom ? n : 0.0F, edge == Position.Left ? n : 0.0F);
  }
  
  public IBorder(IBorder padding)
  {
    this(padding.t(), padding.r(), padding.b(), padding.l());
  }
  
  public float t()
  {
    return this.t;
  }
  
  public float b()
  {
    return this.b;
  }
  
  public float l()
  {
    return this.l;
  }
  
  public float r()
  {
    return this.r;
  }
  
  public float t(float n)
  {
    return this.t = n;
  }
  
  public float b(float n)
  {
    return this.b = n;
  }
  
  public float l(float n)
  {
    return this.l = n;
  }
  
  public float r(float n)
  {
    return this.r = n;
  }
  
  public boolean isNonZero()
  {
    return (this.t != 0.0F) || (this.r != 0.0F) || (this.l != 0.0F) || (this.r != 0.0F);
  }
  
  @Deprecated
  public IPoint tl()
  {
    return new IPoint(l(), t());
  }
  
  @Deprecated
  public IPoint tr()
  {
    return new IPoint(r(), t());
  }
  
  @Deprecated
  public IPoint bl()
  {
    return new IPoint(l(), b());
  }
  
  @Deprecated
  public IPoint br()
  {
    return new IPoint(r(), b());
  }
  
  public IBorder add(IBorder o)
  {
    return new IBorder(t() + o.t(), r() + o.r(), b() + o.b(), l() + o.l());
  }
  
  public String toString()
  {
    return t() + "-" + r() + "-" + b() + "-" + l();
  }
  
  public static final IBorder ZERO = new IBorder(0.0F);
  float t;
  float b;
  float l;
  float r;
}
