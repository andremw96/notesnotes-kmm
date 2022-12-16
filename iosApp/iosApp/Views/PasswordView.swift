//
//  PasswordView.swift
//  iosApp
//
//  Created by Quipper Indonesia on 16/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct PasswordView: View {
    let placeholder: String
    @State private var showText: Bool = false
    @State var text: Binding<String>
    var prompt: String
    var onCommit: (()->Void)?
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Image(systemName: "lock")
                    .foregroundColor(Color.white)
                
                ZStack {
                    SecureField(placeholder, text: text, onCommit: {
                        onCommit?()
                    })
                    .opacity(showText ? 0 : 1)
                    
                    if showText {
                        HStack {
                            TextField(placeholder, text: text)
                            
                            Spacer()
                        }
                    }
                }
                
                Button(action: {
                    showText.toggle()
                }, label: {
                    Image(systemName: showText ? "eye.slash.fill" : "eye.fill")
                })
                .accentColor(.secondary)
            }
            .padding()
            .overlay(
                RoundedRectangle(cornerRadius: 10)
                    .stroke(lineWidth: 2)
                    .foregroundColor(Color.white)
            )
            
            Text(prompt)
                .fixedSize(horizontal: false, vertical: true)
                .font(.caption)
        }
    }
}

struct PasswordView_Previews: PreviewProvider {
    static var previews: some View {
        PasswordView(placeholder: "Password", text: .constant("password"), prompt: "please fill password")
    }
}
