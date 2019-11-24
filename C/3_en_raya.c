#include <stdio.h>
#include <conio.h>
#include <windows.h>

int main() {

    int victoriasJug1=0, victoriasJug2=0, partidasEmpatadas=0, turno=0;
    char reinicio;
    char nombreuno[20], nombredos[20];

        printf("Introduce el nombre del jugador 1 (X): ");
        gets(nombreuno);
        printf("\nIntroduce el nombre del jugador 2 (O): ");
        gets(nombredos);

    do {
        int jugador=1, movimiento=0, x, rep=0, empate=0;
        char guia[] = " _ _ _\n|7|8|9|\n|4|5|6|\n|1|2|3|\n - - -";
        char tablero[] = " _ _ _\n| | | |\n| | | |\n| | | |\n - - -";
        char repetido[] = "          ";

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
                for (x = 0; x < 10 ; x++) {
                    if (repetido[x] == '1') {
                        printf("Esa casilla ya esta ocupada.");
                        getch();
                        rep = 1;
                    }
                }
                if (rep == 1) {
                    if (jugador == 1) {
                jugador = 2;
            } else {
                jugador = 1;
            }
                    rep = 0;
                    break;
                }
                if (jugador == 1) {
                    tablero[24] = 'X';
                    empate++;
                } else {
                    tablero[24] = 'O';
                    empate++;
                }
                repetido[1] = '1';
                break;
                case 2:
                for (x = 0; x < 10 ; x++) {
                    if (repetido[x] == '2') {
                        printf("Esa casilla ya esta ocupada.");
                        getch();
                        rep = 1;
                    }
                }
                if (rep == 1) {
                    if (jugador == 1) {
                jugador = 2;
            } else {
                jugador = 1;
            }
                    rep = 0;
                    break;
                }
                if (jugador == 1) {
                    tablero[26] = 'X';
                    empate++;
                } else {
                    tablero[26] = 'O';
                    empate++;
                }
                repetido[2] = '2';
                break;
                case 3:
                for (x = 0; x < 10 ; x++) {
                    if (repetido[x] == '3') {
                        printf("Esa casilla ya esta ocupada.");
                        getch();
                        rep = 1;
                    }
                }
                if (rep == 1) {
                    if (jugador == 1) {
                jugador = 2;
            } else {
                jugador = 1;
            }
                    rep = 0;
                    break;
                }
                if (jugador == 1) {
                    tablero[28] = 'X';
                    empate++;
                } else {
                    tablero[28] = 'O';
                    empate++;
                }
                repetido[3] = '3';
                break;
                case 4:
                for (x = 0; x < 10 ; x++) {
                    if (repetido[x] == '4') {
                        printf("Esa casilla ya esta ocupada.");
                        getch();
                        rep = 1;
                    }
                }
                if (rep == 1) {
                    if (jugador == 1) {
                jugador = 2;
            } else {
                jugador = 1;
            }
                    rep = 0;
                    break;
                }
                if (jugador == 1) {
                    tablero[16] = 'X';
                    empate++;
                } else {
                    tablero[16] = 'O';
                    empate++;
                }
                repetido[4] = '4';
                break;
                case 5:
                for (x = 0; x < 10 ; x++) {
                    if (repetido[x] == '5') {
                        printf("Esa casilla ya esta ocupada.");
                        getch();
                        rep = 1;
                    }
                }
                if (rep == 1) {
                    if (jugador == 1) {
                jugador = 2;
            } else {
                jugador = 1;
            }
                    rep = 0;
                    break;
                }
                if (jugador == 1) {
                    tablero[18] = 'X';
                    empate++;
                } else {
                    tablero[18] = 'O';
                    empate++;
                }
                repetido[5] = '5';
                break;
                case 6:
                for (x = 0; x < 10 ; x++) {
                    if (repetido[x] == '6') {
                        printf("Esa casilla ya esta ocupada.");
                        getch();
                        rep = 1;
                    }
                }
                if (rep == 1) {
                    if (jugador == 1) {
                jugador = 2;
            } else {
                jugador = 1;
            }
                    rep = 0;
                    break;
                }
                if (jugador == 1) {
                    tablero[20] = 'X';
                    empate++;
                } else {
                    tablero[20] = 'O';
                    empate++;
                }
                repetido[6] = '6';
                break;
                case 7:
                for (x = 0; x < 10 ; x++) {
                    if (repetido[x] == '7') {
                        printf("Esa casilla ya esta ocupada.");
                        getch();
                        rep = 1;
                    }
                }
                if (rep == 1) {
                    if (jugador == 1) {
                jugador = 2;
            } else {
                jugador = 1;
            }
                    rep = 0;
                    break;
                }
                if (jugador == 1) {
                    tablero[8] = 'X';
                    empate++;
                } else {
                    tablero[8] = 'O';
                    empate++;
                }
                repetido[7] = '7';
                break;
                case 8:
                for (x = 0; x < 10 ; x++) {
                    if (repetido[x] == '8') {
                        printf("Esa casilla ya esta ocupada.");
                        getch();
                        rep = 1;
                    }
                }
                if (rep == 1) {
                    if (jugador == 1) {
                jugador = 2;
            } else {
                jugador = 1;
            }
                    rep = 0;
                    break;
                }
                if (jugador == 1) {
                    tablero[10] = 'X';
                    empate++;
                } else {
                    tablero[10] = 'O';
                    empate++;
                }
                repetido[8] = '8';
                break;
                case 9:
                for (x = 0; x < 10 ; x++) {
                    if (repetido[x] == '9') {
                        printf("Esa casilla ya esta ocupada.");
                        getch();
                        rep = 1;
                    }
                }
                if (rep == 1) {
                    if (jugador == 1) {
                jugador = 2;
            } else {
                jugador = 1;
            }
                    rep = 0;
                    break;
                }
                if (jugador == 1) {
                    tablero[12] = 'X';
                    empate++;
                } else {
                    tablero[12] = 'O';
                    empate++;
                }
                repetido[9] = '9';
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