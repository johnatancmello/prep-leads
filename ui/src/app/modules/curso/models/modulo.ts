export class Modulo {
  public id: number;
  public nome: string;
  public cargaHoraria: string;
  public status: string;

  public static newModulo(): Modulo {
    const m = new Modulo();
    return m;
  }
}
