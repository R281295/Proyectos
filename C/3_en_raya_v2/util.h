#include <stdio.h>
#include <conio.h>
#include <stdbool.h>

char repetido[] = "          ";
int rep = 0;
int empate = 0;
bool casillaOcupada = false;
int jugador=1;

bool comprobarCasillaOcupada(char casilla) {
	int x;
    for (x = 0; x < 10 ; x++) {
        if (repetido[x] == casilla) {
            printf("Esa casilla ya esta ocupada.");
            getch();
            casillaOcupada = true;
        }
    }
    return casillaOcupada;
}

char *pintarTablero(char tablero[], int jugador, int posicionTablero) {
	if (jugador == 1) {
        tablero[posicionTablero] = 'X';
        empate++;
    } else {
        tablero[posicionTablero] = 'O';
        empate++;
    }
    return tablero;
}

void invertirJugador() {
	if (jugador == 1) {
        jugador = 2;
    } else {
        jugador = 1;
    }
}

int getPosicionCasilla(int posicion) {
	int posicionCasilla[] = {0, 24, 26, 28, 16, 18, 20, 8, 10, 12};
	return posicionCasilla[posicion];
}
