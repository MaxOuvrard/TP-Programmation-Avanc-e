import matplotlib.pyplot as plt
import numpy as np

def lire_fichier(nom_fichier):
    with open(nom_fichier, "r") as file:
        lines = file.readlines()
    
    erreur, processus_total, nb_worker, temps_ms = [], [], [], []
    
    for line in lines:
        values = line.strip().split()
        if len(values) == 4:  # Vérifier que la ligne contient bien 4 valeurs
            erreur.append(float(values[0]))
            processus_total.append(int(values[1]))
            nb_worker.append(int(values[2]))
            temps_ms.append(float(values[3]))
    
    return processus_total, nb_worker, temps_ms

def calcul_speedup(processus_total, nb_worker, temps_ms):
    if 1 in nb_worker:
        index_1 = nb_worker.index(1)
        T1 = temps_ms[index_1]
        Ntot1 = processus_total[index_1]
    else:
        raise ValueError("Aucune donnée pour nb_worker=1, vérifiez le fichier d'entrée.")
    
    speedup_faible = [(T1 * Ntot1) / (temps_ms[i] * processus_total[i]) for i in range(len(nb_worker))]
    
    unique_workers = sorted(set(nb_worker))
    avg_speedup = []
    for worker in unique_workers:
        indices = [i for i, w in enumerate(nb_worker) if w == worker]
        avg_speedup.append(sum(speedup_faible[i] for i in indices) / len(indices))
    
    return unique_workers, avg_speedup

# Lire les données de deux fichiers
fichiers = ["out-pif.txt"]
colors = ['b', 'g']
labels = ["Speedup pour 120 000 000 points"]

plt.figure(figsize=(8, 5))  # Taille rectangulaire

for i, fichier in enumerate(fichiers):
    processus_total, nb_worker, temps_ms = lire_fichier(fichier)
    unique_workers, avg_speedup = calcul_speedup(processus_total, nb_worker, temps_ms)
    plt.plot(unique_workers, avg_speedup, marker='o', linestyle='-', color=colors[i], label=labels[i])

# Tracer la courbe idéale (Speedup = 1)
plt.axhline(y=1, linestyle='--', color='r', label='Speedup idéal')

# Ajouter un fond quadrillé avec des rectangles
plt.grid(visible=True, which='both', linestyle='--', linewidth=0.5)
plt.minorticks_on()

# Ajouter des labels et une légende
plt.xlabel("Nombre de Threads")
plt.ylabel("Speedup")
plt.title("Courbe du Speedup pour Pi.java (Faible)")
plt.legend()
plt.show()
