/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{

    public static void testZeroBlue(){
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

    public static void testNegate(){
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.negate();
        beach.explore();
    }

    public static void testGrayscale(){
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.grayscale();
        beach.explore();
    }

    public static void testMirrorArms(){
        Picture snowman = new Picture("snowman.jpg");
        snowman.explore();
        snowman.mirrorArms();
        snowman.explore();
    }

    public static void testMirrorGulls(){
        Picture seagull = new Picture("seagull.jpg");
        seagull.explore();
        seagull.mirrorGulls();
        seagull.explore();
    }

    public static void testFixUnderwater(){
        Picture fishes = new Picture("water.jpg");
        fishes.explore();
        fishes.fixUnderwater();
        System.out.println("Fixed underwater");
        fishes.explore();
    }

    public static void testMirrorVertical(){
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorVertical();
        caterpillar.explore();
    }

    public static void testCollage()
    {
        Picture canvas = new Picture("beach.jpg");
        canvas.createCollage();
        canvas.explore();
    }

    public static void testMirrorTemple(){
        Picture temple = new Picture("temple.jpg");
        temple.explore();
        temple.mirrorTemple();
        temple.explore();
    }

    public static void testMirrorDiagonal(){
        Picture beach = new Picture ("beach.jpg");
        beach.explore();
        beach.mirrorDiagonal();
        beach.explore();
    }

    public static void testKeepOnlyBlue(){
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.keepOnlyBlue();
        beach.explore();
    }

    public static void testMirrorHorizontal(){
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorHorizontal();
        caterpillar.explore();
    }

    public static void testEdgeDetection()
    {
        Picture swan = new Picture("swan.jpg");
        swan.explore();
        swan.edgeDetection(27);
        swan.explore();
    }

    public static void testEdgeDetection2()
    {
        Picture swan = new Picture("swan.jpg");
        swan.explore();
        swan.edgeDetection(27);
        swan.explore();
    }

    public static void testMirrorHorizontalBottomToTop(){
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorHorizontalBotToTop();
        caterpillar.explore();
    }

    public static void main(String[] args){
        // uncomment a call here to run a test
        // and comment out the ones you don't want
        // to run
        testZeroBlue();
        testKeepOnlyBlue();
        //testKeepOnlyRed();
        //testKeepOnlyGreen();
        testNegate();
        testGrayscale();
        testFixUnderwater();
        testMirrorVertical();
        testMirrorTemple();
        testMirrorArms();
        testMirrorGulls();
        testMirrorDiagonal();
        testCollage();
        //testCopy();
        testEdgeDetection();
        testEdgeDetection2();
        //testChromakey();
        //testEncodeAndDecode();
        //testGetCountRedOverValue(250);
        //testSetRedToHalfValueInTopHalf();
        //testClearBlueOverValue(200);
        //testGetAverageForColumn(0);
    }
}