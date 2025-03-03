import matplotlib.pyplot as plt
import pandas as pd
import os
import matplotlib.ticker as ticker

def read_data(file_path):
    # Lire les données du fichier et les retourner sous forme de DataFrame
    data = pd.read_csv(file_path, delim_whitespace=True, header=None)
    data.columns = ['Total_Process', 'Other_Column', 'Error_Value', 'Other_Column2']
    return data

def plot_errors_from_files(file_paths):
    # Initialiser la figure
    plt.figure(figsize=(10, 6))
    
    # Pour chaque fichier
    for file_path in file_paths:
        if os.path.exists(file_path):
            data = read_data(file_path)
            
            # Afficher les erreurs sous forme de points
            plt.scatter(data['Total_Process'], data['Error_Value'], label=os.path.basename(file_path), s=10)  # 's=10' pour définir la taille des points
        else:
            print(f"Le fichier {file_path} n'existe pas.")
    
    # Personnaliser le graphique
    plt.xlabel('Nombre total de processus')
    plt.ylabel('Erreur')

    # Appliquer le format scientifique sur l'axe des ordonnées
    plt.gca().yaxis.set_major_formatter(ticker.FuncFormatter(lambda x, _: f'{x:.2e}'))

    plt.title('Erreur en fonction du nombre total de processus')
    plt.legend()
    plt.grid(True)
    plt.show()

# Liste des fichiers à traiter
file_paths = [
    "out-pi1.txt", # Remplacer par vos fichiers réels
    "out-pi2.txt", # Remplacer par vos fichiers réels
    "fichier3.txt", # Remplacer par vos fichiers réels
]

# Appeler la fonction de tracé
plot_errors_from_files(file_paths)
