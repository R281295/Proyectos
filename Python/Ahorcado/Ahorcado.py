def pedirPalabra():
    return input("Escribe tu palabra: ")

def toAsteriscos(frase):
    asteriscos = ""
    for i in range(len(frase)):
        if frase[i] == " ":
            asteriscos += " "
        else:
            asteriscos += "*"
    return asteriscos

def pedirLetra():
    try:
        return input("Elige una letra: ")[0]
    except:
        return ""

def pintarMuneco(errores):
    switcher = {
        1: "_____\n|  O\n|\n|\n|\n",
        2: "_____\n|  O\n|  |\n|\n|\n",
        3: "_____\n|  O\n| /|\n|\n|\n",
        4: "_____\n|  O\n| /|\\\n|\n|\n",
        5: "_____\n|  O\n| /|\\\n|  |\n|\n",
        6: "_____\n|  O\n| /|\\\n|  |\n| /\n",
        7: "_____\n|  O\n| /|\\\n|  |\n| / \\\n"
    }
    print(switcher[errores])

def desvelarLetras(frase, letra, asteriscos):
    newAsteriscos = ""
    global errores
    if asteriscos.count("*") == 0:
        errores = maximoErroresPermitidos+1
    if frase.count(letra) == 0:
        errores += 1
        print(f"Tienes {errores}/{maximoErroresPermitidos} fallos")
        pintarMuneco(errores)
    for i in range(len(frase)):
        if frase[i] == letra:
            newAsteriscos += letra
        else:
            newAsteriscos += asteriscos[i]
    return newAsteriscos

maximoErroresPermitidos = 7
errores = 0
frase = pedirPalabra()
asteriscos = toAsteriscos(frase)
print(asteriscos)

letrasDichas = ""
while(errores < maximoErroresPermitidos and asteriscos.count("*") > 0):
    letra = pedirLetra()
    if(letrasDichas.count(letra) == 0):
        letrasDichas += letra
        asteriscos = desvelarLetras(frase, letra, asteriscos)
    else:
        print("Ya has dicho esa letra")
    print(asteriscos+"\n")

if(errores == maximoErroresPermitidos):
    print(f"Has perdido... la palabra era: {frase}")
else:
    print("Enhorabuena! Has ganado!!")




