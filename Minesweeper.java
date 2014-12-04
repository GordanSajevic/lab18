package lab18;

import java.util.Scanner;

public class Minesweeper {

	public static int[][] poljeZaIgru()
	{
		
		int visina = 5;
		int sirina = 5;
		int[][] polje = new int[visina][sirina];
		return polje;
	}
	
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
	
	private static int randomNumber(int min, int max) {
		int broj = (int)(Math.random()*(max)) + min;
		return broj;
	}
	
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

	public static void napraviPolje()
	{
		int pocetak = 0;
		int kraj = 5;
		int[][] matrica = poljeZaIgru();
		matrica = postaviMine(matrica, pocetak, kraj);
		dodajKecinu(matrica);
		ispisiPolje(matrica);
	}
	
	
	public static void main(String[] args) {
		napraviPolje();
	}


}
