import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgForOf} from "@angular/common";
import {CorsoService} from "../service/corso/corso.service";
import {CorsoDto} from "../model/CorsoDto";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-shop',
  standalone: true,
  imports: [
    NgForOf,
    RouterLink
  ],
  templateUrl: './shop.component.html',
  styleUrl: './shop.component.css'
})
export class ShopComponent {

  constructor() {
  }


 @Input()
  corso : CorsoDto;



}
