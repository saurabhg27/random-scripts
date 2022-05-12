import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NegativeIndent {

	public static void main(String[] args) {

		
		if(args.length<2) {
			System.err.println("Usage: enter <inputfile> <outputfile> as args");
			return;
		}
		try {
			String in = args[0];
			String out = args[1];
			if(in.equalsIgnoreCase(out)) {
				System.err.println("Don't try to be smart ʕ ͡° ʖ̯ ͡°ʔ");
				return;
			}
			NegativeIndent thiss = new NegativeIndent(in, out);
			thiss.readFile();
			thiss.writeFile();
			System.out.println("Done, you can parddy now !!");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public NegativeIndent(String in,String out) {
		this.inFileName = in;
		this.outfileName = out;
	}

	String inFileName = "";
	String outfileName = "";
	int max = -1;

	private void writeFile() throws IOException {
		System.out.println("Writing to "+outfileName);
		try (BufferedReader br = new BufferedReader(new FileReader(inFileName));
				FileWriter fr = new FileWriter(outfileName)) {
			String line;
			while ((line = br.readLine()) != null) {
				int i=0;
				while(i<line.length() && (line.charAt(i) == ' ' || line.charAt(i) == '\t') ) {
					i++;
				}
				//System.out.println("i>>> "+i);
				for(int j=i;j<max;j++)fr.append('\t');
				while(i<line.length()) {
					fr.append(line.charAt(i));
					i++;
				}
				fr.write("\n");
			}
		}
	}

	private void readFile() throws IOException {
		System.out.println("Reading from "+inFileName);
		try (BufferedReader br = new BufferedReader(new FileReader(inFileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				int spcount = 0, i = 0;
				while (i < line.length()) {
					if (line.charAt(i) == ' ' || line.charAt(i) == '\t') {
						spcount++;
					} else {
						break;
					}
					i++;
				}

				max = Math.max(max, spcount);
				//System.out.println("max: " + max + " sp: " + spcount + " - " + line);
			}
		}
	}

}
