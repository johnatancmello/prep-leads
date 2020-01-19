import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

import { NavBarService } from '../../services/nav-bar.service';
import { Router } from '@angular/router';
import {SecurityOauthService} from '../../../security/services/auth.service';

@Component({
  selector: 'app-menu-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class MenuNavbarComponent implements OnInit {

  @Input() class: string;
  @Output() burguerEvent = new EventEmitter();

  constructor(
    private auth: SecurityOauthService,
    private navBarService: NavBarService,
    private router: Router,
  ) { }

  ngOnInit() {
  }

  public getTitle(): string {
    return this.router.url.replace('/', '');
  }

  public getNomeUsuario() {
    const nome = this.auth.getNomeFuncionario();
    if (nome && nome.length > 0) {
      return nome.split(' ')[0];
    } else {
      return '';
    }
  }

  public getSaldacao(): string {
    const hora = new Date().getHours();
    if ((hora >= 0) && (hora < 12)) {
      return 'Bom dia';
    } else if ((hora >= 12) && (hora < 18)) {
      return 'Boa tade';
    } else {
      return 'Boa noite';
    }
  }

  private burguerToggler() {
    this.burguerEvent.emit();
  }

}
