
# Instructions de démarrage

## Démarrer les services Docker

Pour démarrer les services Docker, exécutez la commande suivante :

```sh
docker-compose up -d --build
```

## Démarrer les applications

Démarrez les applications dans l'ordre suivant :

1. discovery
2. gateway
3. product
4. order
5. student

## Pour le frontend

Accédez au répertoire `frontend` et exécutez les commandes suivantes :

```sh
cd frontend
npm i -g pnpm
pnpm i
pnpm run dev
```
