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

Dans cette partie, nous allons chercher à paralléliser l'algorithme pour calculer pi avec la méthode de Monte Carlo. Pour ce faire, nous allons réaliser un descriptif des tâches, puis nous chercherons à faire plusieurs nouveaux algorithmes afin de trouver la méthode qui semble la plus optimisé en parallélisation.

Débutons la parallélisation :

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

```text
boucle parallèle de 0 à n_total
    x = aléatoire(0,1)
    y = aléatoire(0,1)
    si x^2 + y^2 <= 1 alors
        n_cible++
    fin si
fin boucle parallèle
pi = (n_cible / n_total) * 4
```
Le code ci-dessous, l'algorithme 2, présente une version parallélisée de l'algorithme 1. Mais comment avons nous réussi à paralléliser ce programme ? 

Pour cela nous avons créer trois choses : une boucle parallèle, une section critique et une ressource critique. 

La boucle parallèle, clairement indiquée dans l'algorithme, permet à plusieurs itérations de pouvoir s'exécuter en même temps. 

La section critique correspond à l’incrémentation de n_cible, qui est une opération partagée entre plusieurs itérations de la boucle parallèle. Comme plusieurs threads peuvent tenter de modifier cette variable simultanément.

La ressource critique est la variable n_cible, qui stocke le nombre total de points tombant à l’intérieur du quart de cercle. Puisqu’elle est partagée entre toutes les instances parallèles du programme, c'est cette ressource qui créé la section critique.

Algorithme 3:

```text
boucle parallèle de 0 à n_total/100
    boucle de 0 à 100
        x = aléatoire(0,1)
        y = aléatoire(0,1)
        si x^2 + y^2 <= 1 alors
            n_cible++
        fin si
    fin boucle
fin boucle
pi = (n_cible / n_total) * 4
```
Le code ci-dessus, l'algorithme 3, présente une version parallélisée de l'algorithme 1. 

Nous avons créé, comme pour l'algorithme 2, trois choses : une boucle parallèle, une section critique et une ressource critique.

La boucle parallèle, permet toujours d’exécuter plusieurs itérations simultanément à la seule différence ici que chaque itération de cette boucle génère 100 points aléatoires, au lieu de un seul, et vérifie s’ils appartiennent au quart de cercle unité.

La section critique correspond à l’incrémentation de n_cible, qui compte le nombre total de points à l'intérieur du cercle.

La ressource critique est la variable n_cible.

Cette version permet de réduire les accès concurrents à la ressource critique.

Le Master Worker :

Tout d'abord, rappelons ce qu'est le paradigme Master/Worker et comment il fonctionne. Le paradigme Master-Worker est un modèle de programmation parallèle où le Master divise le travail en plusieurs tâches et les distribue aux Workers.

Les Workers exécutent ces tâches indépendamment et renvoient les résultats au Master.

Le Master récupère, combine et finalise les résultats.

Voici un dessin montrant comment fonctionne ce paradigme :

![Dessin paradigme Master/Worker]()

Sur ce schéma, nous pouvons observer que le master est seul et qu'il envoie à plusieurs Worker des données et tâches à réaliser. Une fois ces tâches réalisées par les Workers, ils les renvoient vers le Master qui récolte les données calculées.

Voici le code : 

```text
nb_workers = 100
nb_point = n_total / nb_workers
ncible[nb_workers]
boucle pour de i allant de 0 à nb_point-1
    xi = aléatoire(0,1)
    yi = aléatoire(0,1)
    si dans quart de cercle
      ncible[k]++
    fin si
fin boucle pour
n_cible = sum(ncibles);
pi = 4 * n_cible / n_tot;
```

Chaque worker génère des points et compte ceux qui sont présent dans l'arc de cercle.
On effectue une réduction parallèle pour sommer les résultats.
Enfin, π est calculé à partir du rapport des points dans le cercle.
Ce code est plus efficace que les autres car chaque worker effectue des calculs indépendants, et seule l'opération de somme nécessite une synchronisation.

## III. Mise en oeuvre

![Diagramme Pi Monte Carlo](img/PiMonteCarlo.png)

### Analyse PiMonteCarlo.java

- **Initialisation** :
  - `PiMonteCarlo` initialisé avec `nThrows` (nombre total de points).
  - `nAtomSuccess` compte les points dans un quart de cercle.

- **Génération de Points** :
  - Classe `MonteCarlo` génère des points aléatoires `(x, y)`.
  - Incrémente `nAtomSuccess` si `x² + y² ≤ 1`.

- **Exécution Concurrente** :
  - `getPi` utilise tous les processeurs pour exécuter `MonteCarlo`.
  - Attend la fin des tâches.

- **Calcul de π** :
  - Estime π avec `4 * (succès / total)`.

- **Retour de la Valeur** :
  - Retourne la valeur estimée de π.

### Analyse Assigment102.java

## IV. Calcul de performances

![Scalabilité Forte Pi](img/Scalabilite_forte_pi.png)

![Scalabilité Faible Pi](img/Scalabilite_faible_pi.png)

![Scalabité Forte Assigment 102](im/Scalabilite_forte_pi.png)

![Scalabité Faible Assigment 102](im/Scalabilite_faible_pi.png)

## V. Analyse des erreurs

## VI. Socket

### Analyse MasterSocket.java

### 1. Initialisation
- Le master demande combien de workers (processus) seront utilisés et ouvre un socket pour chacun.

### 2. Distribution des Tâches
- Chaque worker reçoit le nombre total de points à générer pour estimer π.

### 3. Calcul par les Workers
- Les workers génèrent des points aléatoires dans un carré, comptent ceux qui tombent dans un quart de cercle, et renvoient leurs résultats au master.

### 4. Collecte des Résultats
- Le master récupère les résultats des workers et les combine pour estimer π.

### 5. Affichage des Résultats
- Le master affiche la valeur de π, l'erreur relative, et les statistiques de performance.

### 6. Fermeture
- Les sockets entre le master et les workers sont fermés proprement après la simulation.

### Analyse WorkerSocket.java

### Initialisation 
  - Le worker s'initialise en configurant un socket serveur sur un port spécifié. Il attend une connexion du master.

### Distribution des Tâches 
- Le worker lit le nombre total de points à générer à partir du master.

### Calcul 
- Le worker calcule le nombre de points qui tombent dans un quart de cercle en utilisant la méthode de Monte Carlo.

### Collecte des Résultats
- Le worker envoie le résultat calculé au master.

### Affichage des Résultats 
- Cette étape est gérée par le master, qui collecte et affiche les résultats finaux.

### Fermeture des Connexions
- Après le calcul et l'envoi des résultats, le worker ferme tous les sockets et flux pour libérer les ressources.

## Conclusion