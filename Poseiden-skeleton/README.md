![Logo.png](.readme%2FLogo.png)

# Poseidon

Poseidon est une application web de gestion de portefeuille boursier.

## Installation

Ces instructions vous permettront d'obtenir une copie du projet sur votre machine locale à des fins de développement et
de test.

### Prérequis

* Installer <a target="_blank" href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html">Java
  17+</a>
* Installer <a target="_blank" href="https://docs.docker.com/get-docker/">Docker</a>
  et <a target="_blank" href="https://docs.docker.com/compose/install/">Docker Compose</a>

### <p id="starting">Démarrage</p>

Démarrage de l'outil de gestion du projet (<a href="https://github.com/NextSap/ProjectManager/tree/java.oc-7">voir les sources</a>) :

```bash
java -jar Manager.jar true # true indique au programme que vous utilisez une machine Windows, ne mettez aucun argument si vous utilisez une machine MacOS ou Linux
```

Grâce à cet outil, vous pouvez :
- Créer, lancer, éteindre et supprimer les containers Docker de l'application
- Gérer les secrets de l'application de manière sécurisée

### Identifiants de connexion

Admin :
- Username : admin@poseidon.com
- Password : Password1@

User :
- Username : user@poseidon.com
- Password : Password1@