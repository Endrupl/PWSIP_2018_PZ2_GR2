﻿#pragma checksum "..\..\Konwersacja.xaml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "E403C5D523EA3B4FF4C9138D15BD9C6D18CEDD40"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using System;
using System.Diagnostics;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Media.Media3D;
using System.Windows.Media.TextFormatting;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Shell;
using komunikator;


namespace komunikator {
    
    
    /// <summary>
    /// KonwersacjaOkno
    /// </summary>
    public partial class KonwersacjaOkno : System.Windows.Window, System.Windows.Markup.IComponentConnector {
        
        
        #line 19 "..\..\Konwersacja.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox wiadomoscTekst;
        
        #line default
        #line hidden
        
        
        #line 20 "..\..\Konwersacja.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button wyslijPrzycisk;
        
        #line default
        #line hidden
        
        
        #line 30 "..\..\Konwersacja.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.ListView czat;
        
        #line default
        #line hidden
        
        
        #line 39 "..\..\Konwersacja.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button zaladujWiecej;
        
        #line default
        #line hidden
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Uri resourceLocater = new System.Uri("/komunikator;component/konwersacja.xaml", System.UriKind.Relative);
            
            #line 1 "..\..\Konwersacja.xaml"
            System.Windows.Application.LoadComponent(this, resourceLocater);
            
            #line default
            #line hidden
        }
        
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Never)]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Design", "CA1033:InterfaceMethodsShouldBeCallableByChildTypes")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Maintainability", "CA1502:AvoidExcessiveComplexity")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1800:DoNotCastUnnecessarily")]
        void System.Windows.Markup.IComponentConnector.Connect(int connectionId, object target) {
            switch (connectionId)
            {
            case 1:
            
            #line 8 "..\..\Konwersacja.xaml"
            ((komunikator.KonwersacjaOkno)(target)).Closing += new System.ComponentModel.CancelEventHandler(this.Window_Closing);
            
            #line default
            #line hidden
            return;
            case 2:
            this.wiadomoscTekst = ((System.Windows.Controls.TextBox)(target));
            
            #line 19 "..\..\Konwersacja.xaml"
            this.wiadomoscTekst.KeyUp += new System.Windows.Input.KeyEventHandler(this.wiadomoscTekst_KeyUp);
            
            #line default
            #line hidden
            return;
            case 3:
            this.wyslijPrzycisk = ((System.Windows.Controls.Button)(target));
            
            #line 20 "..\..\Konwersacja.xaml"
            this.wyslijPrzycisk.Click += new System.Windows.RoutedEventHandler(this.wyslijPrzycisk_Click);
            
            #line default
            #line hidden
            return;
            case 4:
            this.czat = ((System.Windows.Controls.ListView)(target));
            return;
            case 5:
            this.zaladujWiecej = ((System.Windows.Controls.Button)(target));
            
            #line 39 "..\..\Konwersacja.xaml"
            this.zaladujWiecej.Click += new System.Windows.RoutedEventHandler(this.zaladujWiecej_Click);
            
            #line default
            #line hidden
            return;
            }
            this._contentLoaded = true;
        }
    }
}

