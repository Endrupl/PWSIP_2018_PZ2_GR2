   M S B A M L   `   `   `  ����F  Bkomunikator, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null)clr-namespace:komunikatorkomunikator  5   	       komunikator.WyborRozmowcy   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   +localclr-namespace:komunikator     5   	     � Title$  WyborRozmowcy��5   	   $	��500��6   $	��300��6,   $	��500��68   .��5	      �  � ColumnDefinitions 5
   
   �� 5      $��* �6   �� 5      $
��Auto �6   $��75��6,    � RowDefinitions 5   
   �� 5      $��5 �6   �� 5      $��65 �6   �� 5      $
��391* �6   �� 5      $	��29* �6   .+�5   
   �� -   5         ��Name$ szukanyUzytkownik��5      $��27��6-   $��	10,5,10,0q�69    �� TextWrapping$
 Wrap=�6L   $	��Top=�6`   $��16(�6x   $��1��6�   �� 5   
   -   5         ��Name$ dodajUzytkownika��5      $��1��6+   $
��Left=�6;   $��27��6V   $��0,5,0,0q�6b   $	��Top=�6s   $��65��6�   $��8(�6�   $��1��6�   .��5      �� .+�5      ��  �� TextAlignment$ Center=�6   Dodaj6:   �� 5      $ Center=�6   użytkownika6@   5      5      �� 5   
   -   5        $��statusUzytkownika��5      $��1��6,   $��1��6<   $��25��6I   $��90��6U   $��0,30,0,0q�6`   6r   �� 5   
   -   5         ��Name$ kontakty��5      $��2��6$   $��2��61   $��0,0,0,0q�6E   $	��Top=�6V   $��Stretch=�6n   � 5   
   $��3��6   $��2��6    5      �� 5      $	��75* �6#   �� 5       $
��142* �6#   �� 5!      $	��75* �6#   .+�5#      �� -   5        $ otworz��5#      $��Otwórz��6%   $��Stretch=�66   $��	15,0,60,0q�6T   $	��Top=�6g   $	��122��6   $��19��6�   $��1��6�   $��2��6�   �� 5$      -   5        $
 usun��5$      $��Usuń��6#   $��2��62   $��Right=�6B   $	��Top=�6^   $��55��6v   $��19��6�   6�   �� 5%      -   5        $ button1_Copy��5%      $	��Zab��6+   $
��Left=�69   $	��Top=�6T   $��36��6l   $��19��6w   6�   �� 5&      -   5        $ Odblokuj��5&      $
��Odbl��6'   $
��Left=�66   $	��Top=�6Q   $��39��6i   $��41,0,0,0q�6t   $��19��6�   $��2��6�   5'      5(      5)      