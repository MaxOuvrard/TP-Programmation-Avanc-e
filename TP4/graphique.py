import pandas as pd
import matplotlib.pyplot as plt
plt.figure(figsize=(8, 5))
for i in range(3):
    data = pd.read_csv(f'out-pi{i+1}.txt', sep=' ', header=None, names=['nbprocessus', 'temps_en_ms', 'erreur', 'nb_worker'])

    df_avg = data.groupby('nb_worker')['temps_en_ms'].mean().reset_index()

    nWorkers = df_avg['nb_worker']
    durations = df_avg['temps_en_ms']

    t1 = durations.iloc[0]  # Temps d'exécution pour 1 thread
    speedup = t1 / durations

    
    plt.plot(nWorkers, speedup, marker='o', linestyle='-', color='b', label='Speedup mesuré')
    plt.plot(nWorkers, nWorkers, linestyle='--', color='r', label='Speedup linéaire (Sp=p)')
plt.xlabel("Nombre de Workers")
plt.ylabel("Speedup")
plt.title("Courbe du Speedup en fonction des Workers")
plt.legend()
plt.grid()
plt.show()
