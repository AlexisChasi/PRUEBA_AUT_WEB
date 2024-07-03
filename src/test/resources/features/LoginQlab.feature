Feature: Validación del Precio de un Producto

@Pruebaweb
  Scenario: Validación del Precio de un Producto
    Given estoy en la página de la tienda
    And me logueo con mi usuario "alexis.chasi@epn.edu.ec" y clave "LDU2024$a"
    When navego a la categoría "Clothes" y subcategoría "Men"
    And agrego dos unidades del primer producto al carrito
    Then valido en el popup la confirmación del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el título de la página del carrito
    And vuelvo a validar el cálculo de precios en el carrito
