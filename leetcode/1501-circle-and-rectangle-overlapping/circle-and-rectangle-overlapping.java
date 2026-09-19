class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the point on the rectangle closest to the center of the circle
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));
        
        // Calculate the squared distance between the circle's center and this closest point
        int dx = closestX - xCenter;
        int dy = closestY - yCenter;
        
        // Check if the distance is within the radius (using squared distance to avoid floating point precision issues)
        return (dx * dx + dy * dy) <= (radius * radius);
    }
}