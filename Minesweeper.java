package lab18;

import java.util.Scanner;

public class Minesweeper {
	
	/**
	 * Kreira praznu matricu koja čini polje za igru
	 * @return polje
	 */
	
	public static int[][] poljeZaIgru()
	{
		
		int visina = 5;
		int sirina = 5;
		int[][] polje = new int[visina][sirina];
		return polje;
	}
	
	/**
	 * Ispisuje zadanu matricu
	 * @param matrica
	 */
	
	private static void ispisiPolje(int[][] matrica) {
		for(int i=0; i<matrica.length; i++)
		{
			System.out.print("|");
			for (int j=0; j<matrica[i].length; j++)
			{
				System.out.printf("%2d |", matrica[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Vraća random broj za zadani početak i kraj
	 * @param min
	 * @param max
	 * @return broj
	 */
	
	private static int randomNumber(int min, int max) {
		int broj = (int)(Math.random()*(max)) + min;
		return broj;
	}
	
	/**
	 * Postavlja mine na prazno polje pomoću random brojeva i vraća polje saa minama
	 * @param matrica
	 * @param min
	 * @param max
	 * @return
	 */
	
	private static int[][] postaviMine(int[][] matrica, int min, int max) {
		double brojMina = (matrica.length*matrica[0].length)/3;
		brojMina =(int)brojMina;
		int x=0, y=0;
		int brojac = 0;
		while(brojac < brojMina)
		{
			x = randomNumber(min, max);
			y = randomNumber(min, max);
			if (matrica[x][y] != -1)
			{
				matrica[x][y] = -1;
				brojac++;
			}
			
		}
		return matrica;
		
	}
	
	/**
	 * Dodaje brojeve na članove matrice koji predstavljaju broj mina na susjednim poljima 
	 * @param matrica
	 * @return matrica
	 */
	
	public static int[][] dodajKecinu(int[][] matrica)
	{
		for (int i=0; i<matrica.length; i++)
		{
			for (int j=0; j<matrica[i].length; j++)
			{
				if (matrica[i][j] == -1)
				{
					int pocI = i-1;
					int pocJ = j-1;
					int krajI = pocI+2;
					int krajJ = pocJ+2;
					if(pocI<0)
					{
						pocI = 0;
					}
					if(pocJ<0)
					{
						pocJ = 0;
					}
					if(krajI >= matrica.length)
					{
						krajI = matrica.length-1;
					}
					if (krajJ >= matrica[i].length)
					{
						krajJ = matrica[i].length-1;
					}
					for (int k = pocI; k<=krajI; k++)
					{
						for (int m=pocJ; m<=krajJ; m++)
						{
							if (matrica[k][m] != -1)
							{
								matrica[k][m] += 1;
							}
						}
					}
				}
			}
		}
		return matrica;
	}

	/**
	 * Kreira polje za igru popunjeno minama
	 * @return matrica
	 */
	
	public static int[][] napraviPolje()
	{
		int pocetak = 0;
		int kraj = 5;
		int[][] matrica = poljeZaIgru();
		matrica = postaviMine(matrica, pocetak, kraj);
		matrica = dodajKecinu(matrica);
		return matrica;
	}
	
	/**
	 * Funkcija predstavlja tok igre, tj. igra se završava kada igrač nagazi na minu, ili otkrije sva polja
	 * @param matrica
	 */
	
	private static void igra(int[][] matrica) {
		Scanner unos = new Scanner(System.in);
		int[][] laznoPolje = poljeZaIgru();
		ispisiPolje(laznoPolje);
		int x = -1, y = -1;
		int brojac = 0;
		do
		{
			System.out.println("Unesite koordinate: ");
			x = unos.nextInt()-1;
			y = unos.nextInt()-1;
			laznoPolje[x][y] = matrica[x][y];
			if (matrica[x][y] == -1)
			{
				System.out.println("BOOM! Preselio na ahiret!");
				ispisiPolje(matrica);
				break;
			}
			ispisiPolje(laznoPolje);
			brojac++;
			if (brojac == 17)
			{
				System.out.println("Čestitam, pobijedili ste!");
				ispisiPolje(matrica);
				break;
			}
		}while(matrica[x][y] != -1);
		
		
	}
	

	public static void main(String[] args) {
		System.out.println("Dobrodošli u Minesweeper! Cilj igre je otkriti sva polja na tabeli koja nemaju mine.");
		System.out.println("Unesite koordinate polja na koje želite odigrati i pazite da ne stanete na minu.");
		System.out.println("Vodite računa da koordinate moraju biti od 1 do 5.\n");
		int[][] matrica = napraviPolje();
		igra(matrica);
	}

}
