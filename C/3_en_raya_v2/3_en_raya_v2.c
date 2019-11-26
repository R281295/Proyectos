#include <stdio.h>
#include <conio.h>
#include <windows.h>
#include "util.h"





int main() {
	
	

    int victoriasJug1=0, victoriasJug2=0, partidasEmpatadas=0, turno=0;
    char reinicio;
    char nombreuno[20], nombredos[20];

        printf("Introduce el nombre del jugador 1 (X): ");
        gets(nombreuno);
        printf("\nIntroduce el nombre del jugador 2 (O): ");
        gets(nombredos);

    do {
    	
        jugador=1;
		int movimiento=0;
		char tablero[] = " _ _ _\n| | | |\n| | | |\n| | | |\n - - -";
        char guia[] = " _ _ _\n|7|8|9|\n|4|5|6|\n|1|2|3|\n - - -";
        
        system("cls");

        do {
            if (turno == 1) {
                jugador = 2;
                turno = 2;
            }

            system("cls");
            printf("%s: X\n%s: O\n\n",nombreuno, nombredos);
            printf("\n\n%s",guia);
            printf("\n\n%s",tablero);
            if (jugador == 1) {
                printf("\n\nTurno de %s\nElige un movimiento: ",nombreuno);
            } else {
                printf("\n\nTurno de %s\nElige un movimiento: ",nombredos);
            }
            scanf("%i",&movimiento);
            switch (movimiento) {
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
	                if(comprobarCasillaOcupada(movimiento+'0')) {
	                    invertirJugador();
			            casillaOcupada = false;
					} else {
						pintarTablero(tablero, jugador, getPosicionCasilla(movimiento));
	                	repetido[movimiento] = movimiento+'0';
					}
					
	                break;
                default:
	                printf("Opcion no valida.");
	                getch();
	                if (jugador == 1) {
		                jugador = 2;
		            } else {
		                jugador = 1;
		            }
            }
            if (jugador == 1) {
                jugador = 2;
            } else {
                jugador = 1;
            }

            //Empezamos a comprobar si alguien ha ganado
            if ((tablero[24] == 'X' && tablero[26] == 'X' && tablero[28] == 'X') ||
                (tablero[16] == 'X' && tablero[18] == 'X' && tablero[20] == 'X') ||
                (tablero[8]  == 'X' && tablero[10] == 'X' && tablero[12] == 'X') ||
                (tablero[8]  == 'X' && tablero[16] == 'X' && tablero[24] == 'X') ||
                (tablero[10] == 'X' && tablero[18] == 'X' && tablero[26] == 'X') ||
                (tablero[12] == 'X' && tablero[20] == 'X' && tablero[28] == 'X') ||
                (tablero[8]  == 'X' && tablero[18] == 'X' && tablero[28] == 'X') ||
                (tablero[24] == 'X' && tablero[18] == 'X' && tablero[12] == 'X')) {
                    jugador = 0;
            }

            if ((tablero[24] == 'O' && tablero[26] == 'O' && tablero[28] == 'O') ||
                (tablero[16] == 'O' && tablero[18] == 'O' && tablero[20] == 'O') ||
                (tablero[8]  == 'O' && tablero[10] == 'O' && tablero[12] == 'O') ||
                (tablero[8]  == 'O' && tablero[16] == 'O' && tablero[24] == 'O') ||
                (tablero[10] == 'O' && tablero[18] == 'O' && tablero[26] == 'O') ||
                (tablero[12] == 'O' && tablero[20] == 'O' && tablero[28] == 'O') ||
                (tablero[8]  == 'O' && tablero[18] == 'O' && tablero[28] == 'O') ||
                (tablero[24] == 'O' && tablero[18] == 'O' && tablero[12] == 'O')) {
                    break;
            }
        } while (jugador && empate != 9);

            system("cls");
            printf("%s: X\n%s: O\n\n",nombreuno, nombredos);
            printf("\n\n%s",guia);
            printf("\n\n%s",tablero);

            if (empate != 9) {
                if (jugador == 0) {
                    printf("\n\nHas ganado %s!",nombreuno);
                    victoriasJug1++;
                } else {
                    printf("\n\nHas ganado %s!",nombredos);
                    victoriasJug2++;
                }
            } else {
                printf("\n\nEMPATE");
                partidasEmpatadas++;
            }

            printf("\n\nPartidas:\n\t%s: %i\t%s: %i\tEmpates: %i",nombreuno, victoriasJug1, nombredos, victoriasJug2, partidasEmpatadas);
            printf("\n\n%cOtra partida? (s/n): ",168);

            do {
                reinicio = getche();
                switch (reinicio) {
                    case 's':
                    	break;
                    case 'n':
                    	exit(1);
                    default:
	                    printf("\nOpcion incorrecta, escriba 's' para si, y 'n' para no: ");
	                    break;
                }
            } while (reinicio != 's');

            if (turno == 0) {
                turno = 1;
            } else {
                turno = 0;
            }
        } while (reinicio == 's');
}
