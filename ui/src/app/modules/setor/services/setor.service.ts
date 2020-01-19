import { Injectable } from '@angular/core';

import { Setor } from '../models/setor';
import { HttpParams } from '@angular/common/http';
import {SecutiryHttpService} from '../../security/services/http.service';

@Injectable({
  providedIn: 'root'
})
export class SetorService {

  private location = '/setores';

  constructor(private Http: SecutiryHttpService) { }

  adicionar(setor: Setor): Promise<any> {
    return this.Http.adicionar(setor, this.location);
  }

  alterar(setor: Setor): Promise<any> {
    return this.Http.alterar(setor, this.location + '/' + setor.id );
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
