//
//  TextFieldWithPromptView.swift
//  iosApp
//
//  Created by Quipper Indonesia on 16/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct TextFieldWithPromptView: View {
    var sfSymbolName: String
    var placeholder: String
    @Binding var text: String
    var prompt: String
    
    var body: some View {
        VStack(alignment: .leading, content: {
            HStack {
                Image(systemName: sfSymbolName)
                    .foregroundColor(Color.white)
                
                TextField(placeholder, text: $text)
                    .autocapitalization(.none)
            }
            .padding()
            .overlay(
                RoundedRectangle(cornerRadius: 10)
                    .stroke(lineWidth: 2)
                    .foregroundColor(Color("TextFieldOutlineColor"))
            )
            
            Text(prompt)
                .fixedSize(horizontal: false, vertical: true)
                .font(.caption)
                .foregroundColor(Color.red)
        })
    }
}

struct TextFieldWithPromptView_Previews: PreviewProvider {
    static var previews: some View {
        TextFieldWithPromptView(
            sfSymbolName: "person",
            placeholder: "username",
            text: .constant("username"),
            prompt: "please fill username"
        )
    }
}
