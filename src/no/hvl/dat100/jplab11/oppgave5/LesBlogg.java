package no.hvl.dat100.jplab11.oppgave5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import no.hvl.annotations.Exercise;
import no.hvl.annotations.TransformOption;
import no.hvl.dat100.jplab11.oppgave1.*;
import no.hvl.dat100.jplab11.oppgave2.*;
import no.hvl.dat100.jplab11.oppgave3.*;

public class LesBlogg {

	private static String TEKST = "TEKST";
	private static String BILDE = "BILDE";

	@Exercise(id = {5,1}, transformOption = TransformOption.REPLACE_BODY)
	public static Blogg les(String mappe, String filnavn) {

		Blogg samling = null;

		try {

			BufferedReader reader = new BufferedReader(new FileReader(mappe + filnavn));

			String line;

			line = reader.readLine();
			int antall = Integer.parseInt(line);

			samling = new Blogg(antall);
			Innlegg innlegg = null;

			while (antall > 0) {
				line = reader.readLine();

				int id = Integer.parseInt(reader.readLine());
				String bruker = reader.readLine();
				String dato = reader.readLine();
				int likes = Integer.parseInt(reader.readLine());
				String tekst = reader.readLine();

				if (line.equals(BILDE)) {
					String bilde = reader.readLine();
					innlegg = new Bilde(id, bruker, dato, likes, tekst, bilde);
				} else if (line.equals(TEKST)) {
					innlegg = new Tekst(id, bruker, dato, likes, tekst);
				} else {
					System.out.println("Feil i format");
				}

				samling.leggTil(innlegg);
				antall--;
			}

			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Feil med fil");
		} catch (IOException e) {
			System.out.println("Feil med lese linje");
		}

		return samling;
	}
}
