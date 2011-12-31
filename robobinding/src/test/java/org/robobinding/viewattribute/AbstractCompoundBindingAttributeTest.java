/**
 * Copyright 2011 Cheng Wei, Robert Taylor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.robobinding.viewattribute;

import java.util.List;
import java.util.Map;

import org.robobinding.binder.BindingAttribute;
import org.robobinding.binder.BindingAttributeResolver;
import org.robobinding.internal.com_google_common.collect.Maps;

import android.view.View;

/**
 *
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Robert Taylor
 */
public abstract class AbstractCompoundBindingAttributeTest<T extends View>
{
	private BindingAttributeResolver bindingAttributeResolver;
	
	protected abstract T getView();
	protected abstract WidgetViewAttributeProvider<T> getAttributeProvider();
	
	protected void givenAttributes(Attribute... attributes)
	{
		Map<String, String> pendingBindingAttributes = Maps.newHashMap();
		
		for (Attribute attribute : attributes)
			pendingBindingAttributes.put(attribute.name, attribute.value);
		
		bindingAttributeResolver = new BindingAttributeResolver(pendingBindingAttributes);
	}
	
	protected List<BindingAttribute> getResolvedBindingAttributes()
	{
		resolveSupportedBindingAttributes();
		return bindingAttributeResolver.getResolvedBindingAttributes();
	}
	
	protected BindingAttribute getResolvedBindingAttribute()
	{
		return getResolvedBindingAttributes().get(0);
	}
	
	protected void resolveSupportedBindingAttributes()
	{
		resolveSupportedBindingAttributes(getView());
	}
	
	protected void resolveSupportedBindingAttributes(T view)
	{
		getAttributeProvider().resolveSupportedBindingAttributes(view, bindingAttributeResolver, false);
	}
	
	protected static class Attribute
	{
		String name;
		String value;

		public Attribute(String name, String value)
		{
			this.name = name;
			this.value = value;
		}
		
		@Override
		public String toString()
		{
			return name + "=" + value;
		}
	}
	
}
