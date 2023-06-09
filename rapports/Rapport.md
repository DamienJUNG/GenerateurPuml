## Rapport Sae21, Damien JUNG et Gaël SEILER

### Semaine 1

#### Travail fait
Nous avons commencé par faire les 4 premiers points ensemble afin de comprendre et de prendre en main les doclets
ainsi que le fonctionnement des commandes javadoc.
Gaël s'est ensuite chargé de faire les diagrammes de conception et d'analyse, tandis que Damien a écrit le code du pumlDoclet.

#### Mode d'emploi
Pour exécuter le programme, il suffit d'appuyer sur le bouton run.
Ce dernier utilisera de lui-même la commande javadoc avec les arguments suivants :

```
-private -sourcepath ./src -doclet pumlFromJava.PumlDoclet -docletpath /home/jungdamien/Documents/sae/P21/p21Projet/out/production/p21_projet western -d exemples
```


Il est également possible d'utiliser l'argument -out pour spécifier le nom du document.

### Semaine 2

#### Travail fait
Damien a fait le DCc de Java Language API et Gaël a fait le DCa et DCc du package pumlFromJava ainsi que la production
d'un DCa sans relation.

### Semaine 3 - 5
À partir de la Semaine 3 jusqu'à la semaine 5 nous n'avons pas spécialement suivi les étapes du README.
Nous avons commencé par faire les liens entre classes, ainsi que les redirections de types des méthodes pour le dcc et donc tout ce qui est utile pour ces 2 choses.

Donc Damien a fait les lien et a également ajoute plusieurs classes afin de centraliser et améliorer le code, Gaël de son côté a fait
les méthodes et les attributs pour le dcc et le dca.
Ensuite Damien, s'est chargé d'implémenter la possibilité de choisir entre dca et dcc.

Pour ce qui est du Dca et Dcc au lieu de les faire à la main, on a décidé d'attendre sur le projet
afin que ce dernier puisse générer ses propres diagrammes.

### Semaine 6
Pour la semaine 6 nous avons essayé de faire les annotations. Et finalement, nous avons réussi à faire
les annotations de type Override.

