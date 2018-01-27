package org.objects;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by Quinn on 11/29/2017.
 */
public class Entity extends Sprite {


    private int health;
    private int movementSpeed;
    private List<Point2D> vertices;		//Vertices of the object
    private Rectangle2D boundingBox;	//Bounding Box of the object
    private boolean isCollidable;		//If the object can be collided with
    private boolean canMove;					//If the object can move
    private boolean canDisplace;				//If the object can be displaced

    public Entity(float posX, float posY, String image, List<Point2D> vertices, boolean isCollidable, boolean canMove, boolean canDispalce) {
        super(posX, posY, image);

        this.vertices = vertices;
        this.isCollidable = isCollidable;
        this.canMove = canMove;
        this.canDisplace = canDisplace;

        //Determine the Bounding Box
        double top = 0;
        double left = 0;
        double right = 0;
        double bottom = 0;

        //Loop through the vertices of the object to determine the extremes.
        if(vertices != null) {
            for (Point2D point : vertices) {
                if (point.getY() > top) {
                    top = point.getX();
                }
                if (point.getY() < bottom) {
                    bottom = point.getX();
                }
                if (point.getX() > right) {
                    right = point.getX();
                }
                if (point.getX() < left) {
                    left = point.getX();
                }
            }

            //Set the bounding box.
            this.boundingBox = new Rectangle2D.Double(top, left, (right - left), (top - bottom));
        }
    }

    //Get the point that is closest to the edge of the boudning box.
    //Used to determine if there is a collision.
    private Point2D getClosestEdge(Point2D point){
		/*
		Determine all 4 points along the bounding Box edge that may intersect with the object.

		Example:

		    |------x---|
			|      |   |
			x------.---x
		    |      |   |
			|------x---|

		This is only ran if the point is inside the boudning box and these points can be determined.
		*/
        Point2D[] points = new Point2D[4];

        points[0] = new Point((int)point.getX(), (int)(this.boundingBox.getMaxY() + this.posY));			//point on top edge of bounding box.
        points[1] = new Point((int)point.getX(), (int)(this.boundingBox.getMinY() + this.posY)); //point on the bottom edge of bounding box
        points[2] = new Point((int)(this.boundingBox.getMinX() + this.posX), (int)point.getY());			//point on the left side of the bounding box.
        points[3] = new Point((int)(this.boundingBox.getMaxX() + this.posX), (int)point.getY());	//point on the right side of the bounding box

        double smallestDist = 0;
        Point2D closestEdgePoint = null;
        for(Point2D p : points){
            //Create a line between the point in question and the edge of the bounding box.
            Line2D line = new Line2D.Double(p, point);
            //Determine the distance from the point to each edge of the bounding box.
            double distance = Math.sqrt(((line.getX2() - line.getX1())*(line.getX2() - line.getX1())) + ((line.getY2() - line.getY1())*(line.getY2() - line.getY1())));
            //If the distance is the smallest, save it
            if(distance < smallestDist)
            {
                smallestDist = distance;
                closestEdgePoint = p;
            }
        }
        //Send back the closest point on the bounding box.
        return closestEdgePoint;
    }

    public boolean isColliidng(Point2D point){
        //Determine if the point is inside the boudning box
        if(point.getX() > this.boundingBox.getMinX() &&
                point.getX() < this.boundingBox.getMaxX() &&
                point.getY() > this.boundingBox.getMinY() &&
                point.getY() < this.boundingBox.getMaxY())
        {

            //Get the shortest line to the edge of the bounding box.
            Line2D lineToEdge = new Line2D.Double(point, getClosestEdge(point));

            //Create lines between each vertex of the shape.
            //Might just store these as lines instead of points so it doesn't recalculate them each time.
            Point2D lastPoint = null;	//endpoint for line
            for(Point2D vertex : vertices){
                //For each point in the vertex map, draw a line to the next point.
                if(lastPoint == null){
                    //If this is the first point, get the last vertex in the set to makea line with.
                    lastPoint = vertices.get(vertices.size() - 1);
                }
                //Draw a line between the vertices.
                Line2D objectBound = new Line2D.Double(lastPoint, vertex);
                //If the line that you just drew intersects the line from the point in question to the edge of the shape then there is a collision.
                if(lineToEdge.intersectsLine(objectBound)){
                    return true;
                }
                lastPoint = vertex;
            }	//End for
            return false;
        } else {
            return false;	//End if
        }
    }


}
