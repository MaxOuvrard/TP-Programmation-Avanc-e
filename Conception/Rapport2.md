![Logo IUT Vélizy](img/logoIUT.png)
# Rapport et Dossier de Conception
### Ouvrard Maxence INF3-FA

## Introduction

<p style="text-align:justify;">
Le présent document a pour objectif de présenter le rapport et la conception des TP réalisés dans le cadre des cours du module Programmation avancée (INR05A). L'objectif de celui-ci est de présenter le cheminement de pensée de chaque TD/TP en présentant la conception permettant la résolution de chaque question. Ce rapport a pour but de donner suite au premier rapport et reprend les éléments de cours à partir de la méthode de Monte Carlo.
<p>

### Les outils utilisés

<p style="text-align:justify;">
Afin de réaliser ce document, les outils utilisés sont les différents supports de cours, les sujets de TD/TP afin de reprendre les morceaux de codes fournis et les notes prises en cours, certains points seront appuyés de schéma réalisés en cours. De plus, internet est un outil utilisé afin de confimer ou infirmer certaines interrogations. L'utilisation peut être employée mais uniquement dans le cas d'une reformulation de paragraphe. Son utilisation sera notifiée, dans le cas où elle est utilisée dans le document, par un message au dessus du paragraphe retravaillé informatiquement.
<p>

## TD et TP Monte Carlo

### Etude de la méthode de Monte Carlo

<p style="text-align:justify;">
La méthode de Monte Carlo est utilisé au casino et dans les calculs scientifiques.

La probabilité que Xp soit dans le cercle est p = 
</p>

### Parallélisation

### Mise en oeuvre

![Diagramme Pi Monte Carlo](img/PiMonteCarlo.png)

### Analyse MasterSocket.java

Étapes principales dans le code
- **Initialisation des workers** :
    - Le master demande combien de workers (processus) seront utilisés. Il ouvre un socket (canal de communication) pour chaque worker sur un port donné.
- **Envoi des tâches aux workers** :
    - Chaque worker reçoit le nombre total de points à générer pour l'estimation de 𝜋.

- **Traitement par les workers** :
    - Les workers génèrent des points aléatoires dans un carré, comptent ceux qui tombent dans un quart de cercle et renvoient leurs résultats au master.

- **Récupération des résultats** :
    - Le master collecte les résultats des workers via leurs sockets respectifs.
    - Il combine ces résultats pour calculer la valeur approximative de 𝜋.

- **Affichage des résultats** :
    - Le master affiche 𝜋, l'erreur relative, et les statistiques de performance (durée, nombre de points, etc.).
    - L'utilisateur peut choisir de répéter la simulation.

- **Fermeture des sockets** :
    - Une fois la simulation terminée, les sockets entre le master et les workers sont fermés proprement.

Sockets : 
- **Socket côté master** : Utilisé pour envoyer des tâches et recevoir des résultats.
- **Socket côté worker (non montré ici)** : Écoute les messages du master, exécute la tâche, puis renvoie le résultat.