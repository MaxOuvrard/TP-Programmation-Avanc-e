---
math: true
---

![Logo IUT Vélizy](img/logoIUT.png)
# Rapport et Dossier de Conception
### Ouvrard Maxence INF3-FA

## Introduction

<p style="text-align:justify;">
Le présent document a pour objectif de présenter le rapport et la conception des TP réalisés dans le cadre des cours des modules Programmation avancée (INR05A) et 
Qualité de développement (IN5R08A). L'objectif de celui-ci est de présenter le cheminement de pensée de chaque TD/TP en présentant la conception permettant la résolution de chaque question. Ce rapport a pour but de donner suite au premier rapport et reprend les éléments de cours à partir de la méthode de Monte Carlo.
<p>

### Les outils utilisés

<p style="text-align:justify;">
Afin de réaliser ce document, les outils utilisés sont les différents supports de cours, les notes pruses en cours, les sujets de TD/TP afin de reprendre les morceaux de codes fournis et les notes prises en cours, certains points seront appuyés de schéma réalisés en cours. De plus, internet est un outil utilisé afin de confimer ou infirmer certaines interrogations. L'utilisation de l'Intelligence Artificielle peut être employée mais uniquement dans le cas d'une reformulation de paragraphe. Son utilisation sera notifiée, dans le cas où elle est utilisée dans le document, par un message au dessus du paragraphe retravaillé informatiquement.
<p>

## TD et TP Monte Carlo

### Etude de la méthode de Monte Carlo

<p style="text-align:justify;">

## I. Généralités

I. Généralités

Utilisé au casino et au calcul scientifique.

![Quart de cercle](https://via.placeholder.com/150)

Pour $\pi$ :



$d = \sqrt{x^2 + y^2}$

$\pi \approx \frac{x}{r} \cdot \frac{y}{r} \cdot n$

La probabilité que $x_p$ soit dans le cercle où :



Soit $\pi$ à la n-ième. Soit $P(x_p \text{ dans } \leq r)$.

Soit un carré de côté $A$, soit un quart de disque de rayon $r = 1$. L'aire du carré s'écrit :



L'aire du quart de disque s'écrit :



Pour illustrer le tirage aléatoire de points $x_p$ de coordonnées $(x_p, y_p)$ où $x_p$ et $y_p$ suivent une loi $U([0, 1[)$.

La probabilité qu'un point $x_p$ soit dans le quart de disque est :



On effectue $n$ tirs. Si $n$ est grand alors on approche :



avec $n_{\text{cible}}$ le nombre de points dans la cible.

On peut approcher $\pi$ par :



On écrit l'algorithme permettant de calculer $\pi$ de cette manière :
</p>

**Algorithme 1 :**

```text
cmpt = 0
point = 0
tant que point < nb_point
    x = aléatoire(0,1)
    y = aléatoire(0,1)
    si x^2 + y^2 <= 1 alors
        cmpt++
    point++
fin tant que
point = (cmpt / nb_point) * 4
```

## II. Parallélisation

On choisit un modèle de parallélisme de tâches.

- Tâches :
  - Générer des points
  - Calcul des points dans le cercle
  - Calcul de Pi
- Sous-tâches :
  - Initialiser les valeurs
  - Générer x
  - Générer y
  - Vérifier si c'est dans le cercle
  - Calculer pi
  

Algorithme 2 :


Algorithme 3:

## III. Mise en oeuvre

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

## IV. Calcul de performances

![Scalabilité Forte Pi](img/Scalabilite_forte_pi.png)

![Scalabilité Faible Pi](img/Scalabilite_faible_pi.png)

![Scalabité Forte Assigment 102](im/Scalabilite_forte_pi.png)

![Scalabité Faible Assigment 102](im/Scalabilite_faible_pi.png)

## V. Analyse des erreurs

## VI. Socket

## Conclusion