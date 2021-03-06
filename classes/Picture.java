import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    public void zeroBlue(){
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    public void negate(){
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(pixelObj.getRed() - 255);
                pixelObj.setGreen(pixelObj.getGreen() - 255);
                pixelObj.setBlue(pixelObj.getBlue() - 255);
            }
        }
    }

    public void grayscale(){
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {   
                int avg = (int)((pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3);
                pixelObj.setRed(avg);
                pixelObj.setBlue(avg);
                pixelObj.setGreen(avg);
            }
        }
    }

    public void mirrorVertical(){
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorTemple(){
        int mirrorPoint = 245;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        for (int row = 30; row < 100; row++)
        {

            for (int column = 25; column < mirrorPoint; column++)
            {

                leftPixel = pixels[row][column];      
                rightPixel = pixels[row]                       
                [mirrorPoint - column + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    public void invert()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < pixels[0].length; col++)
            {
                int red = 255 - pixels[row][col].getRed();
                int green = 255 - pixels[row][col].getGreen();
                int blue = 255 - pixels[row][col].getBlue();

                Color newColor = new Color(red, green, blue);

                pixels[row][col].setColor(newColor);
            }
        }
    }

    public void copy(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    public void copy2(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    public void fixUnderwater(){
        Pixel[][] pixels = this.getPixels2D();

        int redAvg = 0;
        int greenAvg = 0;
        int blueAvg = 0;
        int totalPix = 0;

        int maxR = 0;
        int minR = 255;
        int maxG = 0;
        int minG = 255;
        int maxB = 0;
        int minB = 255;

        for (int row = 26; row < 36; row++)
        {
            for (int col = 178; col < 198; col++)
            {
                totalPix++;

                Pixel myPixel = pixels[row][col];

                redAvg += myPixel.getRed();
                greenAvg += myPixel.getGreen();
                blueAvg += myPixel.getBlue();

                if (myPixel.getRed() > maxR) { maxR = myPixel.getRed(); }
                if (myPixel.getRed() < minR) { minR = myPixel.getRed(); }
                if (myPixel.getGreen() > maxG) { maxG = myPixel.getGreen(); }
                if (myPixel.getGreen() < minG) { minG = myPixel.getGreen(); }
                if (myPixel.getBlue() > maxB) { maxB = myPixel.getBlue(); }
                if (myPixel.getGreen() < minB) { minB = myPixel.getBlue(); }

            }
        }

        redAvg = redAvg / totalPix;
        greenAvg = greenAvg / totalPix;
        blueAvg = blueAvg / totalPix;

        Color averageColor = new Color(redAvg, greenAvg, blueAvg);

        int rRange = (maxR - minR);
        int gRange = (maxG - minG);
        int bRange = (maxB - minB);

        int redDistance = rRange;
        int greenDistance = gRange;
        int blueDistance = bRange;

        double maxDistance = Math.sqrt(redDistance * redDistance +
                greenDistance * greenDistance +
                blueDistance * blueDistance);

        double tolerance = 1.7;

        for (int row = 0; row < pixels.length; row++) {
            for (int column = 0; column < pixels[0].length; column++){

                Pixel myPix = pixels[row][column];
                boolean closeEnough = myPix.colorDistance(averageColor) < maxDistance * tolerance;

                if (closeEnough){
                    myPix.setBlue(myPix.getBlue() + 50);
                }else{
                    myPix.setBlue(myPix.getBlue() - 50);
                }
            }
        }
    }

    public static void main(String[] args) {
        Picture beach = new Picture("caterpillar.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
        beach.negate();
        beach.grayscale();
    }

    public void mirrorVerticalRightToLeft(){
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int column = 0; column < width / 2; column++)
            {
                leftPixel = pixels[row][column];
                rightPixel = pixels[row][width - 1 - column];
                leftPixel.setColor(rightPixel.getColor());
            }
        }
    }

    public void mirrorHorizontal(){
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;

        int height = pixels.length;
        for (int row = 0; row < height; row++)
        {
            for (int column = 0; column < pixels[0].length; column++)
            {
                topPixel = pixels[row][column];
                bottomPixel = pixels[height - 1 - row][column];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    public void mirrorHorizontalBotToTop(){
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;
        for (int row = 0; row < height; row++)
        {
            for (int column = 0; column < pixels[0].length; column++)
            {
                topPixel = pixels[row][column];
                bottomPixel = pixels[height - 1 - row][column];
                topPixel.setColor(bottomPixel.getColor());
            }
        }
    }

    public void mirrorDiagonal(){
        Pixel[][] pix = this.getPixels2D();
        Pixel topRightPix = null;
        Pixel bottomLeftPix = null;
        int maxLength;
        if (pix.length < pix[0].length){

            maxLength = pix.length; 
        }else {maxLength = pix[0].length; 
        }

        for (int row = 0; row < maxLength; row++){
            for (int col = row; col < maxLength; col++){
                topRightPix = pix[row][col];
                bottomLeftPix = pix[col][row];
                bottomLeftPix.setColor(topRightPix.getColor());
            }
        }
    }

    public void mirrorArms(){
        Pixel topPix = null;
        Pixel bottomPix = null;

        int mirrorPoint = 195;

        Pixel[][] pix = this.getPixels2D();

        for (int row = 160; row < mirrorPoint; row++){
            for (int column = 100; column < 170; column++){
                topPix = pix[row][column];      
                bottomPix = pix[mirrorPoint - row + mirrorPoint][column];
                bottomPix.setColor(topPix.getColor());
            }
        }

        int mirrorPoint2 = 200;
        Pixel topPix2 = null;
        Pixel bottomPix2 = null;

        for (int row = 170; row < mirrorPoint2; row++){
            for (int column = 240; column < 290; column++){
                topPix2 = pix[row][column];      
                bottomPix2 = pix[mirrorPoint2 - row + mirrorPoint2][column];
                bottomPix2.setColor(topPix2.getColor());
            }
        }
    }

    public void mirrorGulls(){
        int mirrorPoint = 350;
        Pixel rightPix = null;
        Pixel leftPix = null;
        Pixel[][] pix = this.getPixels2D();  
        for (int row = 240; row < 330; row++)
        {
            for (int col = 240; col < mirrorPoint; col++)
            {
                rightPix = pix[row][col];      
                leftPix = pix[row][mirrorPoint - col + mirrorPoint/3];
                leftPix.setColor(rightPix.getColor());
            }
        }
    }

    public void copy2(Picture fromPic, int startRow, int endRow, int startCol, int endCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < endRow;
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < endCol;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
                //System.out.println("Fromrow " + fromRow + " toRow " + toRow + " fromCol" + fromCol + " toCol " + toCol);
                //System.out.println(fromPixels[0].length);
            }
        } 
    }

    public void createCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");

        //this.copy(flower1,100,0);
        this.copy2(flower1,0,100, 0, 100);
        //flower1.explore();
        //this.copy(flower1,200,0);
        // Mirroring
        int mirrorPoint = 98;
        Pixel rightPixel = null;
        Pixel leftPixel = null;
        Pixel[][] pixels = this.getPixels2D();   
        for (int row = 0; row < 98; row++)
        {
            for (int col = 0; col < 88; col++)
            {
                rightPixel = pixels[row][col];      
                leftPixel = pixels[mirrorPoint - row + mirrorPoint][col];
                leftPixel.setColor(rightPixel.getColor());
            }
        }
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy2(flowerNoBlue,300,350,80,500);

        Picture flowerinverse = new Picture(flower2);
        flowerinverse.invert();
        this.copy2(flowerinverse, 100, 300, 80, 300);
        //this.copy(flowerNoBlue,300,0);
        //this.copy(flower1,400,0);
        //this.copy(flower2,500,0);
        //this.mirrorVertical();

        this.write("collage.jpg");
    }

    public void keepOnlyBlue()
    {
        Pixel[][] pix = this.getPixels2D();
        for (Pixel[] rowArray : pix)
        {
            for (Pixel pixObj : rowArray)
            {
                pixObj.setRed(0);
                pixObj.setGreen(0);
            }
        }
    }

    public void myCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        this.copy2(flower1,10,20, 0, 100);
        
        this.write("mycollage.jpg");
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPix = null;
        Pixel rightPix = null;
        Pixel topPix = null;
        Pixel bottomPix = null;

        Pixel[][] pixels = this.getPixels2D();
        for (int row = 0; row < pixels.length - 1; row++)
        {
            for (int column = 0; column < pixels[0].length-1; column++)
            {
                leftPix = pixels[row][column];
                rightPix = pixels[row][column+1];
                topPix = pixels[row][column];
                bottomPix = pixels[row + 1][column];
                if (leftPix.colorDistance(rightPix.getColor()) > edgeDist ||
                topPix.colorDistance(bottomPix.getColor()) > edgeDist)
                    leftPix.setColor(Color.BLACK);
                else
                    leftPix.setColor(Color.WHITE);
            }
        }
    }

        public void edgeDetection2(int edgeDist)
    {
        Pixel leftPix = null;
        Pixel rightPix = null;
        Pixel topPix = null;
        Pixel bottomPix = null;

        Pixel[][] pixels = this.getPixels2D();
        for (int row = 0; row < pixels.length - 1; row++)
        {
            for (int column = 0; column < pixels[0].length-1; column++)
            {
                leftPix = pixels[row][column];
                rightPix = pixels[row][column+1];
                topPix = pixels[row][column];
                bottomPix = pixels[row + 1][column];
                if (leftPix.colorDistance(rightPix.getColor()) > edgeDist ||
                topPix.colorDistance(bottomPix.getColor()) > edgeDist)
                    leftPix.setColor(Color.BLACK);
                else
                    leftPix.setColor(Color.WHITE);
            }
        }
    }

    
    public double colorDistance(Color testColor1, Color testColor2)
    {
        double redDistance = testColor2.getRed() - testColor1.getRed();
        double greenDistance = testColor2.getGreen() - testColor1.getGreen();
        double blueDistance = testColor2.getBlue() - testColor1.getBlue();
        double distance = Math.sqrt(redDistance * redDistance + 
                greenDistance * greenDistance +
                blueDistance * blueDistance);
        return distance;
    }

    public Color getAverageColor(Color[] myColors)
    {
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;

        int total = 0;

        for (Color currentColor : myColors)
        {
            totalRed += currentColor.getRed();
            totalGreen += currentColor.getGreen();
            totalBlue += currentColor.getBlue();
            total++;
        }

        return new Color(totalRed / total, totalGreen / total, totalBlue / total);

    }

    public Color[] getPixelColors(ArrayList<Pixel> pixels)
    {
        Color[] myColors = new Color[pixels.size()];
        int count = 0;
        for (Pixel currentPixel : pixels)
        {
            myColors[count] = currentPixel.getColor();
            count++;
        }

        return myColors;
    }

    public Pixel[][] getPixelCluster(Pixel[][] pixels, int startRow, int startCol,
    int width, int height)
    {
        Pixel[][] pixelCluster = new Pixel[height][width];

        if (pixels.length < startRow + height || pixels[0].length < startCol + width)
        {
            return pixelCluster;
        }

        for (int row = startRow; row < startRow + height; row++)
        {
            for (int col = startCol; col < startCol + width; col++)
            {
                pixelCluster[row - startRow][col - startCol] = pixels[row][col];
            }
        }

        return pixelCluster;
    }

    /**
     * Method getPartialArray takes an array of pixels,
     * an angle to divide the array, and the "type of" that
     * determines whether it returns the top/right (0) or
     * the bottom/left (1)
     * 
     * This one only takes the elements that lie on the line of division
     * 
     * Need to update this method to match the one below ********
     * 
     * @param pixels 
     * @param angle the angle to divide, given in radians (0 to pi)
     */
    public ArrayList<Pixel> getPartialArrayLine(Pixel[][] fullArray, double angle, int typeOf)
    {
        int rows = fullArray.length, cols = fullArray[0].length;
        int centerRow = rows / 2, centerCol = cols / 2;
        int arrayLength = (rows * cols);
        ArrayList<Pixel> tempList = new ArrayList<Pixel>();

        double slope = Math.tan(angle);
        if (slope == 0) slope = 0.001;
        double newSlope = -1 / slope;

        for (int i = 0; i < arrayLength; i++)
        {
            int currentRow = i / cols, currentCol = i % cols;
            //System.out.println(i + "\t" + currentRow + " " + currentCol);
            if (currentCol == (newSlope * (currentRow - centerRow) + centerCol))
            // this tests whether the current cell is above/below the "line"
            {
                if (typeOf == 1)
                {
                    tempList.add(fullArray[currentRow][currentCol]);
                    //System.out.println("added " + i);
                }
            }
            else
            {
                if (typeOf == 0)
                {
                    tempList.add(fullArray[currentRow][currentCol]);
                    //System.out.println("added " + i);
                }
            }
        }

        return tempList;
    }

    /**
     * Method getPartialArray takes an array of pixels,
     * an angle to divide the array, and the "type of" that
     * determines whether it returns the top/right (0) or
     * the bottom/left (1)
     * 
     * Need to update this method to match the one below ********
     * 
     * @param pixels 
     * @param angle the angle to divide, given in radians (0 to pi)
     */
    public ArrayList<Pixel> getPartialArray(Pixel[][] fullArray, double angle, int typeOf)
    {
        int rows = fullArray.length, cols = fullArray[0].length;
        int centerRow = rows / 2, centerCol = cols / 2;
        int arrayLength = (rows * cols);
        ArrayList<Pixel> tempList = new ArrayList<Pixel>();

        double slope = Math.tan(angle);
        if (slope == 0) slope = 0.001;
        double newSlope = -1 / slope;

        for (int i = 0; i < arrayLength; i++)
        {
            int currentRow = i / cols, currentCol = i % cols;
            //System.out.println(i + "\t" + currentRow + " " + currentCol);
            if (currentCol < (newSlope * (currentRow - centerRow) + centerCol))
            // this tests whether the current cell is above/below the "line"
            {
                if (typeOf == 1)
                {
                    tempList.add(fullArray[currentRow][currentCol]);
                    //System.out.println("added " + i);
                }
            }
            else
            {
                if (typeOf == 0)
                {
                    tempList.add(fullArray[currentRow][currentCol]);
                    //System.out.println("added " + i);
                }
            }
        }

        return tempList;
    }

    public static ArrayList<Integer> getPartialArray(int[][] fullArray, double angle, int typeOf)
    {
        int rows = fullArray.length, cols = fullArray[0].length;
        int centerRow = rows / 2, centerCol = cols / 2;
        int arrayLength = (rows * cols);
        ArrayList<Integer> tempList = new ArrayList<Integer>();

        double slope = Math.tan(angle);
        if (slope == 0) slope = 0.001;
        double newSlope = -1 / slope;

        for (int i = 0; i < arrayLength; i++)
        {
            int currentRow = i / cols, currentCol = i % cols;
            System.out.println(i + "\t" + currentRow + " " + currentCol);
            if (currentCol < (newSlope * (currentRow - centerRow) + centerCol))
            {
                if (typeOf == 1)
                {
                    tempList.add(fullArray[currentRow][currentCol]);
                    System.out.println("added " + i);
                }
            }
            else
            {
                if (typeOf == 0)
                {

                    tempList.add(fullArray[currentRow][currentCol]);
                    System.out.println("added " + i);
                }
            }
        }

        return tempList;
    }

} // this } is the end of class Picture, put all new methods before this
