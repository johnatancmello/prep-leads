import { Formacao } from './formacao';

export class Area {
  public id: number;
  public nome: string;
  public areaPrecedente: Area;
  public formacoes: Array<Formacao>;

  public static newArea(): Area {
    const a = new Area();
    a.areaPrecedente = new Area();
    return a;
  }
}
