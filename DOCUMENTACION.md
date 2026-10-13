# Documentación Técnica: Módulo de Cálculo de Compras y Descuentos (`PurchaseCalculator`)

## 1. Descripción del problema
En el desarrollo de una tienda en línea, el procesamiento correcto de las transacciones financieras es un pilar fundamental para la experiencia del usuario y la integridad financiera del negocio. Este módulo se encarga de realizar operaciones críticas del backend, tales como el cálculo del subtotal de los productos adquiridos, la validación de la viabilidad de la compra, la aplicación de políticas de descuento por volumen y la obtención del precio final a pagar. 

Verificar estas reglas mediante pruebas automatizadas es vital para prevenir cobros erróneos, evitar pérdidas económicas por descuentos mal aplicados y asegurar que las restricciones de entrada (como precios o cantidades inválidas) se manejen adecuadamente.

---

## 2. Reglas de negocio
El sistema opera bajo las siguientes reglas de negocio predefinidas:
* **Cálculo del subtotal (`calculateSubtotal`):** Se obtiene multiplicando el precio unitario del producto por la cantidad solicitada.
* **Validación de la compra (`isValidPurchase`):** Una compra se considera válida únicamente si tanto el precio unitario como la cantidad son estrictamente mayores a cero ($> 0$).
* **Cálculo del descuento (`calculateDiscount`):** Se otorga un descuento del $10\%$ ($0.10$) sobre el subtotal siempre y cuando este sea igual o superior a $\$1,000$ pesos mexicanos ($\ge 1000$). Si el subtotal es inferior a $\$1,000$, el descuento es de $\$0.0$.
* **Cálculo del precio final (`calculateFinalPrice`):** Se determina restando el monto del descuento correspondiente al subtotal de la compra.

---

## 3. Casos de prueba
La siguiente tabla detalla los casos de prueba implementados en la clase `PurchaseCalculatorTest` para verificar el correcto funcionamiento de cada método:

| Método Evaluado | Caso de Prueba / Descripción | Datos de Entrada | Resultado Esperado | Resultado Obtenido | Propósito del Caso |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `calculateSubtotal` | Subtotal estándar | precio = $100.0$, cantidad = $3$ | $300.0$ | $300.0$ | Verificar multiplicación con valores positivos habituales. |
| `calculateSubtotal` | Subtotal con cantidad unitaria | precio = $400.0$, cantidad = $1$ | $400.0$ | $400.0$ | Comprobar el comportamiento con una cantidad mínima válida. |
| `calculateSubtotal` | Subtotal con precio cero | precio = $0.0$, cantidad = $1$ | $0.0$ | $0.0$ | Evaluar el comportamiento límite cuando el precio es cero. |
| `isValidPurchase` | Compra válida regular | precio = $200.0$, cantidad = $2$ | `true` | `true` | Asegurar que valores positivos válidos sean aceptados. |
| `isValidPurchase` | Precio igual a cero | precio = $0.0$, cantidad = $2$ | `false` | `false` | Validar que un precio de cero rechace la transacción. |
| `isValidPurchase` | Cantidad igual a cero | precio = $20.0$, cantidad = $0$ | `false` | `false` | Validar que una cantidad de cero rechace la transacción. |
| `isValidPurchase` | Precio negativo | precio = $-320.0$, cantidad = $2$ | `false` | `false` | Comprobar el rechazo ante entradas con precios negativos. |
| `calculateDiscount` | Subtotal inferior al límite | subtotal = $600.0$ | $0.0$ | $0.0$ | Verificar que no se aplique descuento por debajo del umbral de $\$1,000$. |
| `calculateDiscount` | Subtotal exactamente en el límite | subtotal = $1000.0$ | $100.0$ | $100.0$ | Comprobar la condición de frontera inferior ($\ge 1000$). |
| `calculateDiscount` | Subtotal superior al límite | subtotal = $2000.0$ | $200.0$ | $200.0$ | Verificar el cálculo porcentual correcto para compras grandes. |
| `calculateFinalPrice` | Precio final sin descuento | subtotal = $500.0$ | $500.0$ | $500.0$ | Asegurar que el precio final coincida con el subtotal si no hay descuento. |
| `calculateFinalPrice` | Precio final con descuento por límite | subtotal = $1000.0$ | $900.0$ | $900.0$ | Verificar la correcta aplicación de la resta del descuento en el límite. |
| `calculateFinalPrice` | Precio final con descuento estándar | subtotal = $1200.0$ | $1080.0$ | $1080.0$ | Comprobar el flujo completo integrando `calculateDiscount`. |

---

## 4. Resultados de ejecución
Todas las pruebas unitarias diseñadas en la clase `PurchaseCalculatorTest` fueron ejecutadas de manera exitosa utilizando el entorno de desarrollo y JUnit. 
* **Total de pruebas ejecutadas:** $13$
* **Pruebas exitosas (Passed):** $13$
* **Pruebas fallidas (Failed):** $0$

El sistema confirmó que la lógica implementada cumple con todas las expectativas de negocio establecidas.

---

## 5. Errores que se pueden prevenir
Mediante el diseño y ejecución rigurosa de estas pruebas unitarias, es posible prevenir errores comunes de programación y lógica de negocio, entre los cuales destacan:
1. **Errores de operador en condiciones de frontera (Off-by-one / operador incorrecto):** Utilizar estrictamente el operador mayor que ($>$ 1000) en lugar del operador correcto mayor o igual ($\ge 1000$) habría provocado que compras exactamente de $\$1,000$ no recibieran su descuento. La prueba con subtotal de $\$1,000$ previene este fallo.
2. **Falta de validación en entradas negativas o nulas:** Permitir precios o cantidades en cero o negativas podría ocasionar que el sistema calcule subtotales negativos o divida/multiplique de forma errónea, afectando los registros contables y generando frustración en el usuario final.

---

## 6. Conclusión
Las pruebas unitarias son una herramienta indispensable en el desarrollo de software moderno, especialmente en entornos de comercio electrónico (*e-commerce*). Como se demostró en este ejercicio, verificar de forma independiente componentes como el cálculo de subtotales, validaciones y descuentos garantiza que el software sea robusto, predecible y confiable ante casos límite. Contar con un conjunto de pruebas automatizadas reduce drásticamente el riesgo de errores en producción, protegiendo tanto los intereses financieros de la tienda como la confianza del cliente.