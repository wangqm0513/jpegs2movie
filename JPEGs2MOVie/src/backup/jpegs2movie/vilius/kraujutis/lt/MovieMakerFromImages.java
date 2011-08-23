package backup.jpegs2movie.vilius.kraujutis.lt;

import java.io.File;
import java.util.LinkedList;
import java.util.Vector;

import javax.media.MediaLocator;

import org.apollo.jmf.test.JpegImagesToMovie;

public class MovieMakerFromImages {

	private File outputFile;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MovieMakerFromImages movieMakerFromImages = new MovieMakerFromImages();
		movieMakerFromImages
				.setImagesFolderName("C:/docs/workspaces/eclipse/indigo/RoofCamera/images/2011-08-19");
		movieMakerFromImages
				.setOutputFileName("C:/docs/workspaces/eclipse/indigo/RoofCamera/images/2011-08-19.mov");
		try {
			movieMakerFromImages.makeMovie();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void makeMovie() throws Exception {
		com.sun.media.util.Registry.set("secure.allowSaveFileFromApplets",
				Boolean.TRUE);
		
		Vector<String> imageFilesPathsVector = getImageFilesPathsVector();

		JpegImagesToMovie jpegImagesToMovie = new JpegImagesToMovie();
		// Generate the output media locators.
		MediaLocator oml;

		if ((oml = JpegImagesToMovie.createMediaLocator(outputFile
				.getAbsolutePath())) == null) {
			System.err.println("Cannot build media locator from: "
					+ outputFile.getAbsolutePath());
			System.exit(0);
		}
		jpegImagesToMovie.doIt(640, 360, 30, imageFilesPathsVector, oml);
	}

	public LinkedList<File> getImageFilesLinkedList() {
		String[] imageFilesArray = imagesFolder.list();
		LinkedList<File> imagesLinkedList = new LinkedList<File>();
		for (String imageFileName : imageFilesArray) {
			if (!imageFileName.toLowerCase().endsWith("jpg"))
				continue;
			imagesLinkedList.add(new File(imagesFolder.getAbsolutePath()
					+ File.separator + imageFileName));
		}
		return imagesLinkedList;
	}

	private Vector<String> getImageFilesPathsVector() {
		String[] imageFilesArray = imagesFolder.list();
		Vector<String> imageFilesPathsVector = new Vector<String>();
		for (String imageFileName : imageFilesArray) {
			if (!imageFileName.toLowerCase().endsWith("jpg"))
				continue;
			imageFilesPathsVector.add(imagesFolder.getAbsolutePath()
					+ File.separator + imageFileName);
		}
		return imageFilesPathsVector;
	}

	private void setOutputFileName(String outputFileName) {
		outputFile = new File(outputFileName);
	}

	private File imagesFolder;

	private void setImagesFolderName(String imagesFolderName) {
		imagesFolder = new File(imagesFolderName);
	}

}
