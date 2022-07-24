package ball_sort_java;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

public class ImageReader {

	static final boolean debug = Solver.debug;

	public static void main(String args[]) throws IOException {

		readColors("C:\\Users\\hello\\Desktop\\pic.jpg",6,5);
		//readColors("pic.jpg",6,5);

	}

	/**
	 * reads the image and forms the grid of flasks and prints to the console.
	 * Then copy paste this into the Solver
	 * @param imagePath file path
	 * @param _N1 number of flasks in first row of the image
	 * @param _N2 number of flasks in second row of the image(incl. empty ones)
	 * @throws IOException
	 */
	static void readColors(String imagePath,int _N1,int _N2) {
		File file = new File(imagePath);
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		int h = 2400;
		int w = 1080;
		int N1 = _N1;//6;
		int N2 = _N2;//5;
		int hStart1 = 850;
		int hStart2 = 1525;

		int[][] colMat1 = new int[4][N1];

		for (int i = 0; i < N1; i++) {

			int x = ((i + 1) * (w / N1)) - 80;

			for (int j = 0; j < 4; j++) {
				int y = hStart1 + ((j * 100));
				int col = image.getRGB(x, y);
				// System.out.println(x+" "+y+" "+col);
				colMat1[j][i] = col;
			}
		}

		if (debug) {
			for (int[] row : colMat1) {
				System.out.println(Arrays.toString(row));
			}
		}

		int[][] colMat2 = new int[4][N2];
		for (int i = 0; i < N2; i++) {
			int x = ((i + 1) * (w / N2)) - 80;
			for (int j = 0; j < 4; j++) {
				int y = hStart2 + ((j * 100));
				int col = image.getRGB(x, y);
				// System.out.println(x+" "+y+" "+col);
				colMat2[j][i] = col;
			}
		}
		if (debug) {
			for (int[] row : colMat2) {
				System.out.println(Arrays.toString(row));
			}
		}

		char start = 'a';
		Map<Integer, String> map = new HashMap<>();
		List<List<String>> finalList = new ArrayList<>();
		for (int i = 0; i < N1; i++) {
			List<String> li = new ArrayList<>();
			for (int j = 0; j < 4; j++) {

				if (!map.containsKey(colMat1[j][i])) {
					map.put(colMat1[j][i], "" + start);
					start++;
				}

				String color = map.get(colMat1[j][i]);
				li.add(color);
			}
			finalList.add(li);
		}

		for (int i = 0; i < N2; i++) {
			List<String> li = new ArrayList<>();
			for (int j = 0; j < 4; j++) {

				if (!map.containsKey(colMat2[j][i])) {
					map.put(colMat2[j][i], "" + start);
					start++;
				}

				String color = map.get(colMat2[j][i]);
				li.add(color);
			}
			finalList.add(li);
		}

		//remove last 2 empty flasks information
		finalList.remove(finalList.size() - 1);
		finalList.remove(finalList.size() - 1);
		
		if (debug) {
			System.out.println(finalList);
		}

		String ans = finalList.stream()
				.map(li -> String.join("", li))
				.map(s -> new StringBuilder(s).reverse().toString())
				.collect(Collectors.joining("\",\""));

		System.out.println("{\"" + ans + "\",\"\",\"\"}");
		System.out.println("Total number of flasks: " + (N1 + N2));
	}

}
