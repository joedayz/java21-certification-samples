package pe.joedayz.sealedclasses;

public sealed class Product permits Food, Drink{
}


// Si usas clases sealed, tus hijos tienen que tomar 3 caminos posibles:
// 1) Tambien es sealed
// 2) Soy final, no quiero hijos
// 3) Soy non-sealed, abierto a la vida, todos los hijos que vengan

non-sealed class  Food extends Product{

}

final class  Drink extends Product{

}

//class Otro extends Product {} NO PUEDO HEREDAR DE PRODUCT, PORQUE EL SOLO PERMITE 2 HIJOS

class OtroFood extends Food{}
class LowFood extends Food{}

//class Hijo extends Drink{}  COMO ES FINAL, NO PUED TENER HIJOS