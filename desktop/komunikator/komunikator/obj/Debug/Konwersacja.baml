   M S B A M L   `   `   `  ����F  Bkomunikator, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null)clr-namespace:komunikatorkomunikator  5   	   !    komunikator.KonwersacjaOkno   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   +localclr-namespace:komunikator     5   	   -   5          � Title$  Konwersacja��5   	   $	��300��6   $	��500��6*   .��5	      � $��0,31,0,0q�6    � ColumnDefinitions 5
   
   �� 5      $��* �6   �� 5      $
��Auto �6    � RowDefinitions 5   
   �� 5      $
��Auto �6   �� 5      $	��191��6   �� 5      $
��Auto �6   .+�5   
   �� -   5         ��Name$ wiadomoscTekst��5   "   $��0��6   $��	10,0,10,6q�6:    �� TextWrapping$
 Wrap=�6M   $��2��6a   $��23��6n   $��Bottom=�6z   6�   �� 5   
   -   5         ��Name$ wyslijPrzycisk��5      $��Wyślij��6)   $��Right=�6:   $��0,0,8,7q�6V   $��Bottom=�6g   $��59��6�    J� RenderTransformOrigin$ 0.558,0.537&�6�   $��2��6�   $��1��6�   $��23��6�   z�5      V� 5      .~�5      �� �� 5      �� 5       �� Angle$ 0.268Q�6&   T� 5       T� X$ -0.008Q�6)   	 T� Y$	 0.015Q�64   5      5      �� 5   
   -   5        
 ��Name$

 czat��5      $��Stretch=�6!   $��Stretch=�6?   $��1��6[   $��2��6h    �� View 5      �� 5       .P�5!      �� $
��Czas��6%    �� DisplayMemberBinding 63   �� *data+�� 5"      $��Użytkownik��6%    69   �� *
uzytkownik+�� 5#      $��Wiadomość��6%    �� Width$
 1920��6_    68   �� *tresc+6_   5$      5&      �� 5'   
   -   5        $ zaladujWiecej��5'      $��Załaduj więcej��6(   $
��Left=�6A   $��
10,-27,0,0q�6\   $	��Top=�6p   $��92��6�   6�   5(      5)      