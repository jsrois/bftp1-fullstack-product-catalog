## Tareas

- [ ] Crear endpoints en el backend
- [ ] Frontend básico. Mostrar y crear productos.
- [ ] Añadir basic security
- [ ] Sólo los usuarios autenticados pueden añadir productos



### Notas

| Método | Path | Body | Autenticación | Descripción |
|--------|--------|--------|--------|--------|
| `GET`| `/products` | No | No |Devuelve una lista de productos
| `POST`| `/products` | `{ "id": LONG, "name": STRING, "price": double}`| Basic Auth | Crear un producto
| `POST` | `/auth/login` | No | Basic auth | Devuelve 200 si la autenticación es correcta. Devuelve el rol del usuario