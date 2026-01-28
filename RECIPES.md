# RECIPES DATASET

A continuación se encuentran las 90 recetas divididas por objetivo. Este JSON puede ser importado directamente a la colección `recipes` de MongoDB.

## 1. LOSE_WEIGHT (30 recetas)

```json
[
  {
    "name": "Ensalada de Pollo al Limón y Hierbas",
    "goal": "LOSE_WEIGHT",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 25,
    "description": "Una ensalada refrescante y ligera, alta en proteínas y baja en calorías, perfecta para el almuerzo.",
    "portion": 1,
    "calories": 320,
    "image": "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Pechuga de pollo", "quantity": "150g" },
      { "name": "Espinacas baby", "quantity": "2 tazas" },
      { "name": "Tomates cherry", "quantity": "1/2 taza" },
      { "name": "Pepino", "quantity": "1/2 pieza" },
      { "name": "Zumo de limón", "quantity": "1 cucharada" },
      { "name": "Aceite de oliva virgen extra", "quantity": "1 cucharadita" }
    ],
    "steps": [
      "Sazonar el pollo con sal, pimienta y hierbas secas.",
      "Cocinar el pollo a la plancha hasta que esté dorado.",
      "Mezclar las espinacas, tomates y pepino en un bol.",
      "Cortar el pollo en tiras y añadir a la ensalada.",
      "Aliñar con zumo de limón y aceite de oliva."
    ]
  },
  {
    "name": "Sopa de Verduras y Quinoa",
    "goal": "LOSE_WEIGHT",
    "typeFood": "DINNER",
    "vegetarian": true,
    "time": 35,
    "description": "Una sopa reconfortante cargada de fibra y nutrientes para una cena ligera.",
    "portion": 2,
    "calories": 250,
    "image": "https://images.unsplash.com/photo-1547592166-23ac45744acd?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Quinoa", "quantity": "1/2 taza" },
      { "name": "Zanahoria", "quantity": "1 pieza" },
      { "name": "Calabacín", "quantity": "1 pieza" },
      { "name": "Cebolla", "quantity": "1/2 pieza" },
      { "name": "Caldo de verduras bajo en sodio", "quantity": "1 litro" }
    ],
    "steps": [
      "Picar todas las verduras en cubos pequeños.",
      "Sofreír la cebolla en una olla grande con una gota de aceite.",
      "Añadir el resto de verduras y cocinar por 5 minutos.",
      "Añadir la quinoa y el caldo.",
      "Cocinar a fuego lento durante 20 minutos hasta que la quinoa esté lista."
    ]
  },
  {
    "name": "Pescado Blanco al Horno con Espárragos",
    "goal": "LOSE_WEIGHT",
    "typeFood": "DINNER",
    "vegetarian": false,
    "time": 20,
    "description": "Una preparación sencilla y elegante, ideal para mantener el déficit calórico.",
    "portion": 1,
    "calories": 280,
    "image": "https://images.unsplash.com/photo-1519708227418-c8fd9a32b7a2?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Filete de merluza o bacalao", "quantity": "180g" },
      { "name": "Espárragos trigueros", "quantity": "1 manojo" },
      { "name": "Ajo picado", "quantity": "1 diente" },
      { "name": "Pimentón", "quantity": "1 pizca" }
    ],
    "steps": [
      "Precalentar el horno a 200°C.",
      "Colocar el pescado y los espárragos en una bandeja.",
      "Espolvorear el ajo y el pimentón por encima.",
      "Hornear durante 12-15 minutos.",
      "Servir caliente con una rodaja de limón."
    ]
  },
  {
    "name": "Tacos de Lechuga con Atún",
    "goal": "LOSE_WEIGHT",
    "typeFood": "SNACK",
    "vegetarian": false,
    "time": 10,
    "description": "Un snack crujiente y fresco sustituyendo la tortilla por hojas de lechuga.",
    "portion": 1,
    "calories": 180,
    "image": "https://images.unsplash.com/photo-1512152272829-e3139592256f?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Atún al natural", "quantity": "1 lata" },
      { "name": "Hojas de lechuga romana", "quantity": "3 grandes" },
      { "name": "Pico de gallo (tomate, cebolla, cilantro)", "quantity": "1/4 taza" },
      { "name": "Yogur griego natural (opcional)", "quantity": "1 cucharada" }
    ],
    "steps": [
      "Escurrir bien el atún.",
      "Mezclar el atún con el pico de gallo.",
      "Lavar y secar las hojas de lechuga.",
      "Rellenar las hojas con la mezcla de atún.",
      "Añadir un toque de yogur si se desea más cremosidad."
    ]
  },
  {
    "name": "Smoothie de Espinacas y Manzana Verde",
    "goal": "LOSE_WEIGHT",
    "typeFood": "BREAKFAST",
    "vegetarian": true,
    "time": 5,
    "description": "Comienza el día con energía y fibra con este batido verde saciante.",
    "portion": 1,
    "calories": 150,
    "image": "https://images.unsplash.com/photo-1556881286-fc6915169721?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Espinacas frescas", "quantity": "1 puñado" },
      { "name": "Manzana verde", "quantity": "1 pieza" },
      { "name": "Apio", "quantity": "1 tallo" },
      { "name": "Agua de coco", "quantity": "250ml" }
    ],
    "steps": [
      "Lavar bien todos los ingredientes.",
      "Trocear la manzana y el apio.",
      "Batir todo en la licuadora hasta que esté suave.",
      "Servir frío, preferiblemente de inmediato."
    ]
  },
  {
    "name": "Tortilla de Claras con Champiñones",
    "goal": "LOSE_WEIGHT",
    "typeFood": "BREAKFAST",
    "vegetarian": true,
    "time": 15,
    "description": "Desayuno alto en proteínas y muy bajo en grasa.",
    "portion": 1,
    "calories": 220,
    "image": "https://images.unsplash.com/photo-1510693206972-df098062cb71?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Claras de huevo", "quantity": "4 unidades" },
      { "name": "Champiñones laminados", "quantity": "1/2 taza" },
      { "name": "Cebollino", "quantity": "al gusto" }
    ],
    "steps": [
      "Saltear los champiñones en una sartén antiadherente.",
      "Batir las claras ligeramente con sal y pimienta.",
      "Verter las claras sobre los champiñones.",
      "Cocinar a fuego lento hasta que la tortilla esté cuajada.",
      "Decorar con cebollino fresco."
    ]
  },
  {
    "name": "Ceviche Ligero de Tilapia",
    "goal": "LOSE_WEIGHT",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 30,
    "description": "Pescado marinado en cítricos, una explosión de sabor sin grasas añadidas.",
    "portion": 2,
    "calories": 290,
    "image": "https://images.unsplash.com/photo-1534604973900-c41ab4c6d0c0?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Filete de tilapia", "quantity": "300g" },
      { "name": "Zumo de lima", "quantity": "1/2 taza" },
      { "name": "Cebolla morada", "quantity": "1/4 pieza" },
      { "name": "Cilantro", "quantity": "1 puñado" },
      { "name": "Ají picante (opcional)", "quantity": "al gusto" }
    ],
    "steps": [
      "Cortar el pescado en cubos de 1cm.",
      "Cubrir el pescado con el zumo de lima en un bol de vidrio.",
      "Refrigerar por 20 minutos hasta que el pescado esté 'cocido' por el ácido.",
      "Mezclar con la cebolla en plumas, cilantro y ají.",
      "Servir muy frío."
    ]
  },
  {
    "name": "Zoodles (Espaguetis de Calabacín) al Pesto Negro",
    "goal": "LOSE_WEIGHT",
    "typeFood": "LUNCH",
    "vegetarian": true,
    "time": 15,
    "description": "Sustituye la pasta por calabacín para una comida baja en carbohidratos.",
    "portion": 1,
    "calories": 190,
    "image": "https://images.unsplash.com/photo-1584269600464-37b1b58a9fe7?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Calabacín grande", "quantity": "1 pieza" },
      { "name": "Pimienta negra", "quantity": "al gusto" },
      { "name": "Ajo", "quantity": "1 diente" },
      { "name": "Piñones", "quantity": "10g" },
      { "name": "Albahaca fresca", "quantity": "1 taza" }
    ],
    "steps": [
      "Hacer espirales con el calabacín usando un espiralizador.",
      "Saltear los zoodles en una sartén por solo 2-3 minutos (para que queden al dente).",
      "Procesar la albahaca, ajo y piñones para hacer el pesto.",
      "Mezclar suavemente los zoodles con el pesto.",
      "Sazonar con abundante pimienta negra."
    ]
  },
  {
    "name": "Brochetas de Pavo y Piña",
    "goal": "LOSE_WEIGHT",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 20,
    "description": "Combinación agridulce baja en calorías y rica en sabor.",
    "portion": 2,
    "calories": 310,
    "image": "https://images.unsplash.com/photo-1516684732162-798a0062be99?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Pechuga de pavo", "quantity": "250g" },
      { "name": "Piña natural", "quantity": "1/2 unidad" },
      { "name": "Pimiento rojo", "quantity": "1 pieza" },
      { "name": "Salsa de soja baja en sal", "quantity": "1 cucharada" }
    ],
    "steps": [
      "Cortar el pavo, la piña y el pimiento en cubos.",
      "Insertar en palitos de brocheta alternando los ingredientes.",
      "Pincelar con la salsa de soja.",
      "Cocinar en la plancha o grill por 10-12 minutos.",
      "Servir con una ensalada verde pequeña."
    ]
  },
  {
    "name": "Gazpacho Andaluz Tradicional",
    "goal": "LOSE_WEIGHT",
    "typeFood": "SNACK",
    "vegetarian": true,
    "time": 10,
    "description": "Una bebida vitamínica e hidratante, muy baja en calorías.",
    "portion": 4,
    "calories": 120,
    "image": "https://images.unsplash.com/photo-1594943777218-48b61c9e89b2?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Tomates maduros", "quantity": "1 kg" },
      { "name": "Pepino", "quantity": "1 pieza" },
      { "name": "Pimiento verde", "quantity": "1 pieza" },
      { "name": "Vinagre de jerez", "quantity": "2 cucharadas" },
      { "name": "Agua fría", "quantity": "250ml" }
    ],
    "steps": [
      "Lavar y trocear todas las verduras.",
      "Triturar todo en la batidora eléctrica hasta que esté fino.",
      "Pasar por un colador chino para una textura más suave.",
      "Enfriar en la nevera al menos 2 horas.",
      "Servir en vaso o bol con trocitos de pepino."
    ]
  },
  {
    "name": "Brocoli al Vapor con Almendras",
    "goal": "LOSE_WEIGHT",
    "typeFood": "DINNER",
    "vegetarian": true,
    "time": 12,
    "description": "Cena rápida cargada de fibra y grasas saludables controladas.",
    "portion": 1,
    "calories": 210,
    "image": "https://images.unsplash.com/photo-1455243627921-9fce66b37261?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Brócoli", "quantity": "1 cabeza mediana" },
      { "name": "Almendras laminadas", "quantity": "10g" },
      { "name": "Ajo en polvo", "quantity": "al gusto" },
      { "name": "Levadura nutricional", "quantity": "1 cucharada" }
    ],
    "steps": [
      "Lavar y cortar el brócoli en ramilletes.",
      "Cocinar al vapor durante 5-7 minutos hasta que esté tierno pero crujiente.",
      "Tostar ligeramente las almendras en una sartén seca.",
      "Mezclar el brócoli con el ajo y la levadura.",
      "Decorar con las almendras tostadas."
    ]
  },
  {
    "name": "Brochetas de Langostinos y Mango",
    "goal": "LOSE_WEIGHT",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 15,
    "description": "Proteína de mar con un toque tropical dulce y ligero.",
    "portion": 1,
    "calories": 330,
    "image": "https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Langostinos pelados", "quantity": "150g" },
      { "name": "Mango maduro", "quantity": "1/2 pieza" },
      { "name": "Lima", "quantity": "1 pieza" },
      { "name": "Copos de chili", "quantity": "1 pizca" }
    ],
    "steps": [
      "Ensartar los langostinos y trozos de mango en las brochetas.",
      "Sazonar con el zumo de lima y chili.",
      "Grillar 2 minutos por cada lado.",
      "Servir inmediatamente."
    ]
  }
]
```

  {
    "name": "Sopa de Miso con Tofu y Algas",
    "goal": "LOSE_WEIGHT",
    "typeFood": "DINNER",
    "vegetarian": true,
    "time": 10,
    "description": "Una opción japonesa clásica, muy ligera y probiótica.",
    "portion": 2,
    "calories": 140,
    "image": "https://images.unsplash.com/photo-1574071318508-1cdbad80ad38?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Miso blanco", "quantity": "2 cucharadas" },
      { "name": "Tofu firme", "quantity": "100g" },
      { "name": "Alga wakame", "quantity": "5g" },
      { "name": "Cebolleta", "quantity": "1 pieza" }
    ],
    "steps": [
      "Calentar agua sin que llegue a hervir.",
      "Disolver el miso en un poco de agua caliente y añadir al resto.",
      "Añadir el tofu en cubos y las algas.",
      "Cocinar 3 minutos.",
      "Servir con cebolleta picada."
    ]
  },
  {
    "name": "Pechuga de Pollo al Estragón",
    "goal": "LOSE_WEIGHT",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 25,
    "description": "Pollo jugoso con aroma francés, sin salsas pesadas.",
    "portion": 1,
    "calories": 310,
    "image": "https://images.unsplash.com/photo-1604908176997-125f25cc6f3d?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Pechuga de pollo", "quantity": "150g" },
      { "name": "Estragón fresco", "quantity": "1 cucharada" },
      { "name": "Vino blanco (para cocinar)", "quantity": "2 cucharadas" },
      { "name": "Caldo de pollo", "quantity": "50ml" }
    ],
    "steps": [
      "Sellar el pollo en la sartén.",
      "Añadir el vino y dejar evaporar el alcohol.",
      "Añadir el caldo y el estragón.",
      "Tapar y cocinar a fuego lento 10 minutos.",
      "Servir con el jugo resultante."
    ]
  },
  {
    "name": "Ensalada Rusa Fit",
    "goal": "LOSE_WEIGHT",
    "typeFood": "LUNCH",
    "vegetarian": true,
    "time": 20,
    "description": "Versión ligera con yogur griego en lugar de mayonesa.",
    "portion": 2,
    "calories": 240,
    "image": "https://images.unsplash.com/photo-1550304943-4f24f54ddde9?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Patata pequeña", "quantity": "1 pieza" },
      { "name": "Zanahoria", "quantity": "1 pieza" },
      { "name": "Guisantes", "quantity": "1/2 taza" },
      { "name": "Yogur griego natural sin azúcar", "quantity": "125g" },
      { "name": "Mostaza", "quantity": "1 cucharadita" }
    ],
    "steps": [
      "Cocer la patata y zanahoria hasta que estén tiernas.",
      "Enfriar y cortar en dados pequeños.",
      "Mezclar el yogur con la mostaza, sal y pimienta.",
      "Combinar todos los ingredientes.",
      "Dejar enfriar en la nevera antes de servir."
    ]
  },
  {
    "name": "Salteado de Ternera con Pimientos",
    "goal": "LOSE_WEIGHT",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 15,
    "description": "Proteína magra con vegetales salteados estilo wok.",
    "portion": 1,
    "calories": 340,
    "image": "https://images.unsplash.com/photo-1512058560566-42724afbc2db?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Bistec de ternera magro", "quantity": "120g" },
      { "name": "Pimiento verde", "quantity": "1 pieza" },
      { "name": "Pimiento amarillo", "quantity": "1 pieza" },
      { "name": "Jengibre rallado", "quantity": "1 cucharadita" }
    ],
    "steps": [
      "Cortar la ternera en tiras finas.",
      "Saltear a fuego muy alto con el jengibre.",
      "Añadir los pimientos en tiras.",
      "Cocinar 5 minutos sin dejar de remover.",
      "Añadir un toque de soja ligera al final."
    ]
  },
  {
    "name": "Crema de Calabaza y Jengibre",
    "goal": "LOSE_WEIGHT",
    "typeFood": "DINNER",
    "vegetarian": true,
    "time": 25,
    "description": "Cena termogénica y saciante.",
    "portion": 2,
    "calories": 180,
    "image": "https://images.unsplash.com/photo-1476718406336-bb5a9690ee2a?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Calabaza", "quantity": "400g" },
      { "name": "Puerro", "quantity": "1 pieza" },
      { "name": "Jengibre fresco", "quantity": "2cm" },
      { "name": "Agua o caldo", "quantity": "500ml" }
    ],
    "steps": [
      "Rehogar el puerro picado.",
      "Añadir la calabaza troceada y el jengibre.",
      "Cubrir con caldo y cocer 20 minutos.",
      "Triturar hasta obtener una crema suave.",
      "Servir con semillas de calabaza."
    ]
  },
  {
    "name": "Gelatina de Frutos Rojos Natural",
    "goal": "LOSE_WEIGHT",
    "typeFood": "SNACK",
    "vegetarian": false,
    "time": 10,
    "description": "Un dulce sin azúcar añadido, rico en colágeno.",
    "portion": 4,
    "calories": 60,
    "image": "https://images.unsplash.com/photo-1541783245831-57d690ed5833?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Fruta roja variada (fresas, arándanos)", "quantity": "250g" },
      { "name": "Gelatina neutra en láminas", "quantity": "4 piezas" },
      { "name": "Agua", "quantity": "500ml" },
      { "name": "Stevia (opcional)", "quantity": "al gusto" }
    ],
    "steps": [
      "Hidratar las hojas de gelatina en agua fría.",
      "Calentar 250ml de agua y disolver la gelatina.",
      "Mezclar con el resto del agua fría y la fruta troceada.",
      "Verter en moldes.",
      "Refrigerar 4 horas hasta que cuaje."
    ]
  },
  {
    "name": "Carpaccio de Calabacín y Queso Feta",
    "goal": "LOSE_WEIGHT",
    "typeFood": "DINNER",
    "vegetarian": true,
    "time": 10,
    "description": "Entrante o cena muy ligera con queso bajo en grasa.",
    "portion": 1,
    "calories": 160,
    "image": "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Calabacín tierno", "quantity": "1 pieza" },
      { "name": "Queso feta light", "quantity": "30g" },
      { "name": "Zumo de limón", "quantity": "1/2 pieza" },
      { "name": "Menta fresca", "quantity": "unas hojas" }
    ],
    "steps": [
      "Cortar el calabacín en láminas casi transparentes.",
      "Disponer en un plato plano.",
      "Desmenuzar el queso feta encima.",
      "Aliñar con limón, sal y pimienta.",
      "Decorar con la menta fresca picada."
    ]
  },
  {
    "name": "Salmón al Papillote",
    "goal": "LOSE_WEIGHT",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 20,
    "description": "Pescado cocinado en su propio jugo con verduras.",
    "portion": 1,
    "calories": 350,
    "image": "https://images.unsplash.com/photo-1467003909585-2f8a72700288?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Filete de salmón", "quantity": "120g" },
      { "name": "Zanahoria en juliana", "quantity": "1/2 taza" },
      { "name": "Calabacín en juliana", "quantity": "1/2 taza" }
    ],
    "steps": [
      "Poner el salmón y las verduras sobre papel de horno.",
      "Cerrar formando un paquete hermético.",
      "Hornear a 200°C por 15 minutos.",
      "Abrir con cuidado y servir."
    ]
  },
  {
    "name": "Champiñones al Ajillo",
    "goal": "LOSE_WEIGHT",
    "typeFood": "DINNER",
    "vegetarian": true,
    "time": 15,
    "description": "Un clásico español muy saciante y bajo en calorías.",
    "portion": 2,
    "calories": 110,
    "image": "https://images.unsplash.com/photo-1626074353765-517a681e40be?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Champiñones enteros", "quantity": "300g" },
      { "name": "Ajo", "quantity": "3 dientes" },
      { "name": "Guindilla (opcional)", "quantity": "1 pieza" },
      { "name": "Perejil", "quantity": "al gusto" }
    ],
    "steps": [
      "Limpiar y trocear los champiñones.",
      "Dorar los ajos en una sartén con una gota de aceite.",
      "Añadir los champiñones y la guindilla.",
      "Cocinar hasta que suelten el agua y se doren.",
      "Espolvorear perejil fresco al final."
    ]
  },
  {
    "name": "Brochetas de Tofu y Verduras",
    "goal": "LOSE_WEIGHT",
    "typeFood": "LUNCH",
    "vegetarian": true,
    "time": 20,
    "description": "Opción vegana equilibrada para el almuerzo.",
    "portion": 1,
    "calories": 260,
    "image": "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Tofu firme marinado", "quantity": "150g" },
      { "name": "Champiñones", "quantity": "4 piezas" },
      { "name": "Cebolla", "quantity": "1/4 pieza" },
      { "name": "Calabacín", "quantity": "1/2 pieza" }
    ],
    "steps": [
      "Cortar todo en cubos de tamaño similar.",
      "Alternar los ingredientes en palos de brocheta.",
      "Asar en la plancha o grill hasta que doren.",
      "Servir con una cucharada de humus."
    ]
  },
  {
    "name": "Manzana al Horno con Canela",
    "goal": "LOSE_WEIGHT",
    "typeFood": "SNACK",
    "vegetarian": true,
    "time": 25,
    "description": "Postre o snack reconfortante sin azúcares refinados.",
    "portion": 1,
    "calories": 130,
    "image": "https://images.unsplash.com/photo-1568299103215-ff4f7041f0d3?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Manzana tipo Reineta", "quantity": "1 pieza" },
      { "name": "Canela en polvo", "quantity": "1 cucharadita" },
      { "name": "Nueces picadas", "quantity": "2 piezas" }
    ],
    "steps": [
      "Lavar y descorazonar la manzana.",
      "Poner en un recipiente apto para horno.",
      "Rellenar el hueco con canela y nueces.",
      "Hornear a 180°C durante 20 minutos.",
      "Consumir templada."
    ]
  },
  {
    "name": "Ensalada de Atún y Canónigos",
    "goal": "LOSE_WEIGHT",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 10,
    "description": "Comida rápida de montar y muy nutritiva.",
    "portion": 1,
    "calories": 280,
    "image": "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Canónigos", "quantity": "2 tazas" },
      { "name": "Atún al natural", "quantity": "1 lata" },
      { "name": "Huevo duro", "quantity": "1 pieza" },
      { "name": "Olivas negras", "quantity": "5 piezas" }
    ],
    "steps": [
      "Poner los canónigos en la base.",
      "Añadir el atún escurrido.",
      "Cortar el huevo duro en gajos y añadir.",
      "Decorar con las olivas.",
      "Aliñar con vinagre de manzana."
    ]
  },
  {
    "name": "Ternera Estofada con Verduras (Baja Grasa)",
    "goal": "LOSE_WEIGHT",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 60,
    "description": "Guiso tradicional eliminando grasas innecesarias.",
    "portion": 1,
    "calories": 360,
    "image": "https://images.unsplash.com/photo-1534939561126-755e6365d054?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Cuete de ternera", "quantity": "120g" },
      { "name": "Zanahoria", "quantity": "1 pieza" },
      { "name": "Champiñones", "quantity": "1/2 taza" },
      { "name": "Cebolla", "quantity": "1/2 pieza" }
    ],
    "steps": [
      "Dorar la ternera en trozos.",
      "Añadir las verduras y cubrir con agua y vino.",
      "Cocinar a fuego lento durante 45-50 minutos.",
      "Dejar reposar antes de servir."
    ]
  },
  {
    "name": "Pimiento Relleno de Pollo y Quinoa",
    "goal": "LOSE_WEIGHT",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 30,
    "description": "Pimientos de colores rellenos de una mezcla saciante.",
    "portion": 1,
    "calories": 340,
    "image": "https://images.unsplash.com/photo-1563379926898-05f4575a45d8?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Pimiento morrón grande", "quantity": "1 pieza" },
      { "name": "Pollo picado", "quantity": "100g" },
      { "name": "Quinoa cocida", "quantity": "1/2 taza" }
    ],
    "steps": [
      "Cortar la tapa del pimiento y limpiar semillas.",
      "Mezclar el pollo con la quinoa y especias.",
      "Rellenar el pimiento.",
      "Hornear 25 minutos a 190°C.",
      "Servir caliente."
    ]
  },
  {
    "name": "Trucha a la Plancha con Espinacas",
    "goal": "LOSE_WEIGHT",
    "typeFood": "DINNER",
    "vegetarian": false,
    "time": 15,
    "description": "Pescado azul ligero rico en Omega-3.",
    "portion": 1,
    "calories": 290,
    "image": "https://images.unsplash.com/photo-1519708227418-c8fd9a32b7a2?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Filete de trucha", "quantity": "150g" },
      { "name": "Espinacas frescas", "quantity": "2 tazas" },
      { "name": "Ajo", "quantity": "1 diente" }
    ],
    "steps": [
      "Hacer la trucha a la plancha por el lado de la piel.",
      "En otra sartén, saltear las espinacas con ajo.",
      "Servir la trucha sobre la cama de espinacas.",
      "Añadir un chorrito de limón."
    ]
  },
  {
    "name": "Crema de Espárragos Verdes",
    "goal": "LOSE_WEIGHT",
    "typeFood": "DINNER",
    "vegetarian": true,
    "time": 20,
    "description": "Cena ultra ligera y depurativa.",
    "portion": 2,
    "calories": 130,
    "image": "https://images.unsplash.com/photo-1547592166-23ac45744acd?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Espárragos verdes", "quantity": "1 manojo" },
      { "name": "Cebolla", "quantity": "1/2 pieza" },
      { "name": "Puerro", "quantity": "1 pieza" }
    ],
    "steps": [
      "Cortar la base dura de los espárragos.",
      "Cocer los espárragos con la cebolla y el puerro.",
      "Retirar un poco de agua y triturar.",
      "Sazonar con sal y pimienta blanca.",
      "Servir caliente."
    ]
  },
  {
    "name": "Tortitas de Avena y Plátano",
    "goal": "LOSE_WEIGHT",
    "typeFood": "BREAKFAST",
    "vegetarian": true,
    "time": 15,
    "description": "Desayuno dulce y saludable para calmar antojos.",
    "portion": 1,
    "calories": 280,
    "image": "https://images.unsplash.com/photo-1506084868730-342b1f45ff0c?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Copo de avena", "quantity": "40g" },
      { "name": "Plátano maduro pequeñon", "quantity": "1 pieza" },
      { "name": "Clara de huevo", "quantity": "2 unidades" }
    ],
    "steps": [
      "Chafar el plátano con un tenedor.",
      "Mezclar con la avena y las claras.",
      "Hacer pequeñas tortitas en la sartén antiadherente.",
      "Servir con canela por encima."
    ]
  },
  {
    "name": "Infusión de Cúrcuma y Jengibre",
    "goal": "LOSE_WEIGHT",
    "typeFood": "SNACK",
    "vegetarian": true,
    "time": 5,
    "description": "Bebida metabólica y antiinflamatoria sin calorías.",
    "portion": 1,
    "calories": 5,
    "image": "https://images.unsplash.com/photo-1544787210-2213d242687a?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Agua", "quantity": "300ml" },
      { "name": "Cúrcuma fresca rallada", "quantity": "1 pizca" },
      { "name": "Jengibre fresco", "quantity": "1 rodaja" },
      { "name": "Limón", "quantity": "1 rodaja" }
    ],
    "steps": [
      "Hervir el agua con la cúrcuma y el jengibre.",
      "Dejar reposar 3 minutos.",
      "Añadir el limón.",
      "Colar y beber caliente o frío."
    ]
  }
]
```

## 2. GAIN_MUSCLE (30 recetas)

```json
[
  {
    "name": "Bistec de Ternera con Patatas y Espárragos",
    "goal": "GAIN_MUSCLE",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 25,
    "description": "Proteína roja de calidad con carbohidratos complejos para una ganancia muscular óptima.",
    "portion": 1,
    "calories": 580,
    "image": "https://images.unsplash.com/photo-1546964124-0cce460f38ef?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Bistec de ternera", "quantity": "250g" },
      { "name": "Patatas medianas", "quantity": "2 piezas" },
      { "name": "Espárragos trigueros", "quantity": "1 manojo" },
      { "name": "Aceite de oliva", "quantity": "1 cucharada" }
    ],
    "steps": [
      "Hacer las patatas al horno o microondas hasta que estén tiernas.",
      "Salpimentar el bistec y sellar a fuego alto en la plancha.",
      "Cocinar los espárragos junto a la carne por 5 minutos.",
      "Servir todo junto con un toque de romero."
    ]
  },
  {
    "name": "Arroz con Pollo y Aguacate (Post-Entreno)",
    "goal": "GAIN_MUSCLE",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 20,
    "description": "Carga glucémica moderada con grasas saludables y proteína limpia.",
    "portion": 1,
    "calories": 620,
    "image": "https://images.unsplash.com/photo-1512058560566-42724afbc2db?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Arroz blanco o integral", "quantity": "120g seco" },
      { "name": "Pechuga de pollo", "quantity": "200g" },
      { "name": "Aguacate", "quantity": "1/2 pieza" },
      { "name": "Cebolla y ajo", "quantity": "al gusto" }
    ],
    "steps": [
      "Cocer el arroz en agua con sal.",
      "Trocear el pollo y saltear con cebolla y ajo.",
      "Mezclar el arroz con el pollo.",
      "Servir con láminas de aguacate por encima."
    ]
  },
  {
    "name": "Tortilla de 6 Claras y 1 Yema con Atún",
    "goal": "GAIN_MUSCLE",
    "typeFood": "BREAKFAST",
    "vegetarian": false,
    "time": 10,
    "description": "Desayuno súper proteico para empezar el día con los aminoácidos necesarios.",
    "portion": 1,
    "calories": 380,
    "image": "https://images.unsplash.com/photo-1510693206972-df098062cb71?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Claras de huevo", "quantity": "6 unidades" },
      { "name": "Yema de huevo", "quantity": "1 unidad" },
      { "name": "Atún al natural", "quantity": "1 lata" },
      { "name": "Queso fresco batido 0%", "quantity": "50g" }
    ],
    "steps": [
      "Batir las claras y la yema.",
      "Añadir el atún escurrido y el queso fresco.",
      "Cocinar en sartén antiadherente a fuego medio.",
      "Doblar a la mitad y servir caliente."
    ]
  },
  {
    "name": "Batido de Avena, Plátano y Proteína",
    "goal": "GAIN_MUSCLE",
    "typeFood": "SNACK",
    "vegetarian": true,
    "time": 5,
    "description": "Comida líquida rápida y efectiva para aumentar la ingesta calórica.",
    "portion": 1,
    "calories": 450,
    "image": "https://images.unsplash.com/photo-1556881286-fc6915169721?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Leche de vaca o avena", "quantity": "300ml" },
      { "name": "Avena en polvo", "quantity": "50g" },
      { "name": "Plátano", "quantity": "1 pieza" },
      { "name": "Proteína de suero (Whey)", "quantity": "30g" }
    ],
    "steps": [
      "Verter todos los ingredientes en la licuadora.",
      "Procesar durante 30 segundos hasta que no queden grumos.",
      "Beber inmediatamente."
    ]
  },
  {
    "name": "Salmón Grillado con Quinoa y Espinacas",
    "goal": "GAIN_MUSCLE",
    "typeFood": "DINNER",
    "vegetarian": false,
    "time": 25,
    "description": "Cena rica en grasas antiinflamatorias y proteína de alta calidad.",
    "portion": 1,
    "calories": 520,
    "image": "https://images.unsplash.com/photo-1467003909585-2f8a72700288?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Filete de salmón fresco", "quantity": "200g" },
      { "name": "Quinoa cocida", "quantity": "150g" },
      { "name": "Espinacas frescas", "quantity": "2 tazas" },
      { "name": "Sésamo", "quantity": "1 cucharadita" }
    ],
    "steps": [
      "Cocinar el salmón a la plancha.",
      "Saltear las espinacas brevemente con un poco de ajo.",
      "Mezclar las espinacas con la quinoa ya cocida.",
      "Servir el salmón sobre la mezcla y decorar con sésamo."
    ]
  },
  {
    "name": "Pechuga de Pollo Rellena de Requesón y Espinacas",
    "goal": "GAIN_MUSCLE",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 35,
    "description": "Un extra de leucina gracias al requesón para potenciar la síntesis proteica.",
    "portion": 1,
    "calories": 480,
    "image": "https://images.unsplash.com/photo-1604908176997-125f25cc6f3d?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Pechuga de pollo grande", "quantity": "250g" },
      { "name": "Requesón (Ricotta)", "quantity": "100g" },
      { "name": "Espinacas picadas", "quantity": "1/2 taza" },
      { "name": "Aceitunas picadas", "quantity": "al gusto" }
    ],
    "steps": [
      "Abrir la pechuga en forma de libro.",
      "Mezclar el requesón con las espinacas y las aceitunas.",
      "Rellenar el pollo y cerrar con palillos.",
      "Hornear a 200°C durante 25-30 minutos."
    ]
  },
  {
    "name": "Bowl de Yogur Griego, Nueces y Arándanos",
    "goal": "GAIN_MUSCLE",
    "typeFood": "SNACK",
    "vegetarian": true,
    "time": 5,
    "description": "Snack denso en nutrientes y proteína láctea de absorción lenta.",
    "portion": 1,
    "calories": 320,
    "image": "https://images.unsplash.com/photo-1488477181946-6428a0291777?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Yogur griego auténtico", "quantity": "250g" },
      { "name": "Nueces peladas", "quantity": "30g" },
      { "name": "Arándanos frescos", "quantity": "1 puñado" },
      { "name": "Miel pura", "quantity": "1 cucharadita" }
    ],
    "steps": [
      "Poner el yogur en un cuenco.",
      "Añadir las nueces troceadas y los arándanos.",
      "Verter la miel por encima.",
      "Consumir entre comidas principales."
    ]
  },
  {
    "name": "Hamburguesas de Ternera Caseras (Sin Pan)",
    "goal": "GAIN_MUSCLE",
    "typeFood": "DINNER",
    "vegetarian": false,
    "time": 20,
    "description": "Cena alta en hierro y zinc, claves para el entorno anabólico.",
    "portion": 2,
    "calories": 510,
    "image": "https://images.unsplash.com/photo-1550547660-d9450f859349?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Carne de ternera magra picada", "quantity": "300g" },
      { "name": "Cebolla roja", "quantity": "1/2 pieza" },
      { "name": "Huevo (agregado a la masa)", "quantity": "1 unidad" },
      { "name": "Ensalada de tomate y cebolla", "quantity": "guarnición" }
    ],
    "steps": [
      "Mezclar la carne picada con la cebolla muy fina y el huevo.",
      "Formar dos hamburguesas gruesas.",
      "Cocinar a la plancha al punto deseado.",
      "Servir acompañadas de una ensalada fresca."
    ]
  },
  {
    "name": "Garbanzos Salteados con Chorizo de Pavo",
    "goal": "GAIN_MUSCLE",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 15,
    "description": "Legumbres para energía sostenida y proteína vegetal reforzada.",
    "portion": 1,
    "calories": 550,
    "image": "https://images.unsplash.com/photo-1541518763669-27fef04b14ea?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Garbanzos cocidos", "quantity": "250g" },
      { "name": "Chorizo de pavo (bajo en grasa)", "quantity": "50g" },
      { "name": "Pimiento verde", "quantity": "1 pieza" },
      { "name": "Pimentón dulce", "quantity": "1 cucharadita" }
    ],
    "steps": [
      "Picar el chorizo y el pimiento.",
      "Saltear ambos en una sartén con una gota de aceite por 5 min.",
      "Añadir los garbanzos y el pimentón.",
      "Cocinar todo junto otros 5 min y servir."
    ]
  },
  {
    "name": "Filete de Pechuga de Pavo con Arroz Salvaje",
    "goal": "GAIN_MUSCLE",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 25,
    "description": "Comida clásica de culturista: limpia, efectiva y equilibrada.",
    "portion": 1,
    "calories": 590,
    "image": "https://images.unsplash.com/photo-1516684732162-798a0062be99?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Filete de pavo", "quantity": "250g" },
      { "name": "Arroz salvaje o negro", "quantity": "100g seco" },
      { "name": "Hummus de garbanzo", "quantity": "2 cucharadas" }
    ],
    "steps": [
      "Hervir el arroz salvaje (tarda más, unos 20-30 min).",
      "Hacer el pavo a la plancha con pimienta.",
      "Servir el pavo junto al arroz y usar el hummus como salsa saludable."
    ]
  },
  {
    "name": "Hummus Extra con Sticks de Zanahoria y Pan de Pita",
    "goal": "GAIN_MUSCLE",
    "typeFood": "SNACK",
    "vegetarian": true,
    "time": 10,
    "description": "Aperitivo denso en fibra y carbohidratos complejos.",
    "portion": 2,
    "calories": 400,
    "image": "https://images.unsplash.com/photo-1542345842-83569528f1cc?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Hummus tradicional", "quantity": "150g" },
      { "name": "Zanahorias grandes", "quantity": "2 piezas" },
      { "name": "Pan de pita integral", "quantity": "1 unidad" }
    ],
    "steps": [
      "Cortar las zanahorias en bastones.",
      "Tostar ligeramente el pan de pita.",
      "Servir el hummus en un bol central.",
      "Ideal para antes de entrenar."
    ]
  },
  {
    "name": "Bacalao Fresco con Patata Cocida y Pimientos",
    "goal": "GAIN_MUSCLE",
    "typeFood": "DINNER",
    "vegetarian": false,
    "time": 20,
    "description": "Proteína blanca pura con carbohidratos de bajo índice glucémico.",
    "portion": 1,
    "calories": 460,
    "image": "https://images.unsplash.com/photo-1580476262798-bddd9f4b7369?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Bacalao fresco", "quantity": "200g" },
      { "name": "Patatas medianas", "quantity": "2 piezas" },
      { "name": "Pimiento rojo asado", "quantity": "100g" }
    ],
    "steps": [
      "Cocer las patatas con piel por 15-20 min.",
      "Hacer el bacalao al vapor o a la plancha (5-7 min).",
      "Servir el pescado con las patatas peladas y los pimientos por encima."
    ]
  },
  {
    "name": "Porridge de Avena Proteico con Frutas Secas",
    "goal": "GAIN_MUSCLE",
    "typeFood": "BREAKFAST",
    "vegetarian": true,
    "time": 10,
    "description": "Desayuno caliente clásico cargado de energía y fibra.",
    "portion": 1,
    "calories": 530,
    "image": "https://images.unsplash.com/photo-1536304953888-0a624d618497?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Avena en copos suaves", "quantity": "60g" },
      { "name": "Leche entera", "quantity": "250ml" },
      { "name": "Cucharada de proteína vainilla", "quantity": "1 unidad" },
      { "name": "Pasas o dátiles picados", "quantity": "30g" }
    ],
    "steps": [
      "Calentar la leche con la avena a fuego medio removiendo siempre.",
      "Cuando espese, retirar del fuego y añadir la proteína mezclando bien.",
      "Servir en un bol y añadir las frutas secas por encima."
    ]
  },
  {
    "name": "Lentejas Estofadas con Lomo de Cerdo",
    "goal": "GAIN_MUSCLE",
    "typeFood": "LUNCH",
    "vegetarian": false,
    "time": 45,
    "description": "Potente guiso tradicional que aporta hierro y energía duradera.",
    "portion": 2,
    "calories": 610,
    "image": "https://images.unsplash.com/photo-1547928576-965be7f5f6a6?auto=format&fit=crop&q=80&w=800",
    "ingredients": [
      { "name": "Lentejas pardinas", "quantity": "150g seco" },
      { "name": "Lomo de cerdo magro", "quantity": "200g" },
      { "name": "Zanahoria y cebolla", "quantity": "1 pieza cada una" },
      { "name": "Laurel", "quantity": "1 hoja" }
    ],
    "steps": [
      "Hervir las lentejas con las verduras y el laurel.",
      "Trocear el lomo y sellar en una sartén aparte.",
      "Añadir el lomo a las lentejas a mitad de cocción.",
      "Cocinar hasta que la lenteja esté tierna."
    ]
  }
]
```
