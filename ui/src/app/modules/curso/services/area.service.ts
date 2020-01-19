import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';

import { Area } from '../models/area';
import { SegurancaHttpService } from '../../seguranca/services/http.service';

@Injectable({
  providedIn: 'root'
})
export class CursoAreaService {

  private location = '/areas';

  constructor(private Http: SegurancaHttpService) { }

  adicionar(area: Area): Promise<any> {
    return this.Http.adicionar(area, this.location);
  }

  alterar(area: Area): Promise<any> {
    return this.Http.alterar(area, this.location);
  }

  excluir(id: number): Promise<void> {
    return this.Http.excluir(id, this.location);
  }

  pesquisar(params: HttpParams): Promise<any> {
    return this.Http.pesquisar(params, this.location);
  }

  pesquisarById(id: number): Promise<any> {
    return this.Http.pesquisar(null, this.location + '/' + id);
  }
}
