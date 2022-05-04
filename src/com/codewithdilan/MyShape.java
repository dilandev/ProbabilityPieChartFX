package com.codewithdilan;

public abstract class MyShape
{
   private float x1;
   private float y1;
   private float x2;
   private float y2;

   public MyShape()
   {
      this(0, 0, 0, 0);
   }

   public MyShape(float x1, float y1, float x2, float y2)
   {
      this.x1 = (x1 >= 0 ? x1 : 0);
      this.y1 = (y1 >= 0 ? y1 : 0);
      this.x2 = (x2 >= 0 ? x2 : 0);
      this.y2 = (y2 >= 0 ? y2 : 0);
   } 

   public void setX1(float x1)
   {
      this.x1 = (x1 >= 0 ? x1 : 0);
   } 

   public float getX1()
   {
      return x1;
   }

   public void setX2(float x2)
   {
      this.x2 = (x2 >= 0 ? x2 : 0);
   } 

   public float getX2()
   {
      return x2;
   }

   public void setY1(float y1)
   {
      this.y1 = (y1 >= 0 ? y1 : 0);
   }

   public float getY1()
   {
      return y1;
   } 

   public void setY2(float y2)
   { 
      this.y2 = (y2 >= 0 ? y2 : 0);
   } 

   public float getY2()
   {
     return y2;
   }
}
