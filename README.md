# SpaceInvadersSwing

## Project made by Andres Piragauta

Este proyecto es una adaptacion del clásico juego Space Invaders en Java. Proporciona una interfaz gráfica de usuario donde el jugador 
controla una nave espacial derrumbar una invasión alienígena. El juego consta de varios elementos, como la nave del jugador, balas, 
alienígenas y el tiempo de juego.

## Requisitos del Sistema:

- Jdk 17
- Windows 11

## Fucionamieto

Primeramente, el usuario es recibido con una pantalla en la que elije su tecla de disparo, para luego comenzar a jugar. En la parte superior de
la pantalla se encuentran tres datos, el tiempo de juego, numero de enemigos en pantalla y numero de enemigos eliminados por el jugador.

Se usan las flechas izquierda y derecha para mover la nave, cada vez que un alien es impactado por una bala ambos son eliminados del campo
de batalla.

## Configuraciones

Para modificar las configuraciones del juego, por ejemplo velocidad de las balas, aliens o cañon, se encuentra el archivo **config.properties** dentro de
la carpeta **src**. Entre las distintas configuraciones se encuentran:

- Tamaño de la ventana, aliens, balas y cañon
- Velocidad de los aliens, balas y cañon
- Tiempo de aparicion minimo y maximo para cada alien
- Tiempo de refresco para lanzar balas
- Entre otras