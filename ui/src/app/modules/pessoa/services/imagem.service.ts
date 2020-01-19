import { Injectable } from '@angular/core';

import { Imagem } from '../models/imagem';
import { HttpParams } from '@angular/common/http';
import { SegurancaHttpService } from '../../seguranca/services/http.service';

@Injectable({
  providedIn: 'root'
})
export class ImagemService {

  private location = '/imagens';

  constructor(private http: SegurancaHttpService) { }

  adicionar(imagen: Imagem): Promise<any> {
    return this.http.adicionar(imagen, this.location);
  }

  alterar(imagen: Imagem): Promise<any> {
    return this.http.alterar(imagen, this.location);
  }

  excluir(id: number): Promise<void> {
    return this.http.excluir(id, this.location);
  }

  pesquisar(params: HttpParams): Promise<any> {
    return this.http.pesquisar(params, this.location);
  }

  pesquisarById(id: number): Promise<any> {
    return this.http.pesquisar(null, this.location + '/' + id);
  }

}
